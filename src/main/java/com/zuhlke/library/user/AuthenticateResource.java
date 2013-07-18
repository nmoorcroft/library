package com.zuhlke.library.user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonView;
import com.yammer.dropwizard.auth.Auth;
import com.zuhlke.library.domain.User;
import com.zuhlke.library.domain.Views;

@Component
@Path("/authenticate")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticateResource {

    @GET
    @JsonView(Views.Public.class)
    public User authenticate(@Auth User user) {
        return user;
    }
    
}

