package com.zuhlke.library.security;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicCredentials;
import com.zuhlke.library.domain.User;
import com.zuhlke.library.user.UserService;

@Component
public class LibraryAuthenticator implements Authenticator<BasicCredentials, User> {

    @Inject
    private UserService userService;
    
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        User user = userService.findByEmail(credentials.getUsername());
        if (user != null && user.getPassword().equals(credentials.getPassword())) {
            return Optional.of(user);
        } 
        return Optional.absent();
    }
    
}

