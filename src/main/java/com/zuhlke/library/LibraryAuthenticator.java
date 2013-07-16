package com.zuhlke.library;

import com.google.common.base.Optional;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicCredentials;
import com.zuhlke.library.core.User;
import com.zuhlke.library.dao.UserDAO;

public class LibraryAuthenticator implements Authenticator<BasicCredentials, User> {

    private UserDAO userDAO;
    
    public LibraryAuthenticator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        User user = userDAO.findByEmail(credentials.getUsername());
        if (user != null && user.getPassword().equals(credentials.getPassword())) {
            return Optional.of(user);
        }
        return Optional.absent();

    }
    
    
}
