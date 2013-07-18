package com.zuhlke.library.user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.yammer.dropwizard.auth.Auth;
import com.zuhlke.library.domain.User;

@Component
@Path("/authenticate")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticateResource {

    @GET
    public UserAdapter authenticate(@Auth User user) {
        return new UserAdapter(user);
    }
    
}

