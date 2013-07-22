package com.zuhlke.library.security;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.util.B64Code;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.api.model.Parameter;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.server.impl.inject.AbstractHttpContextInjectable;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import com.yammer.dropwizard.auth.Auth;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicCredentials;

/**
 * Custom {@link BasicAuthProvider} that does not respond with WWW-Authenticate header.
 * Prevents the BasicAuth dialog from being displayed by the browser.
 * 
 */
public class CustomAuthProvider<T> implements InjectableProvider<Auth, Parameter> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthProvider.class);
    
    private Authenticator<BasicCredentials, T> authenticator;
    
    public CustomAuthProvider(Authenticator<BasicCredentials, T> authenticator) {
        this.authenticator = authenticator;
    }
    
    @Override
    public Injectable<?> getInjectable(ComponentContext context, Auth auth, Parameter parameter) {
        return new BasicAuthInjectable<T>(authenticator, auth, parameter);
    }
    
    @Override
    public ComponentScope getScope() {
        return ComponentScope.PerRequest;
    }
    
    private static class BasicAuthInjectable<T> extends AbstractHttpContextInjectable<T> {
        
        private static final String PREFIX = "Basic";

        private final Authenticator<BasicCredentials, T> authenticator;
        private final boolean required;
        
        public BasicAuthInjectable(Authenticator<BasicCredentials, T> authenticator, Auth auth, Parameter parameter) {
            this.authenticator = authenticator;
            this.required = auth.required();
        }
        
        @Override
        public T getValue(HttpContext context) {
            final String header = context.getRequest().getHeaderValue(HttpHeaders.AUTHORIZATION);
            try {
                if (header != null) {
                    final int space = header.indexOf(' ');
                    if (space > 0) {
                        final String method = header.substring(0, space);
                        if (PREFIX.equalsIgnoreCase(method)) {
                            final String decoded = B64Code.decode(header.substring(space + 1), StringUtil.__ISO_8859_1);
                            final int i = decoded.indexOf(':');
                            if (i > 0) {
                                final String username = decoded.substring(0, i);
                                final String password = decoded.substring(i + 1);
                                final BasicCredentials credentials = new BasicCredentials(username, password);
                                final Optional<T> result = authenticator.authenticate(credentials);
                                if (result.isPresent()) {
                                    return result.get();
                                }
                            }
                        }
                    }
                }
            } catch (UnsupportedEncodingException e) {
                LOGGER.debug("Error decoding credentials", e);
            } catch (IllegalArgumentException e) {
                LOGGER.debug("Error decoding credentials", e);
            } catch (AuthenticationException e) {
                LOGGER.warn("Error authenticating credentials", e);
                throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
            }

            if (required) {
                throw new WebApplicationException(Response.Status.UNAUTHORIZED);
            }
            
            return null;
        }
        
    }

}