package com.zuhlke.library.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import com.zuhlke.library.core.User;

public class UserDAO extends AbstractDAO<User> {

    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User findByEmail(String email) {
        return uniqueResult(criteria().add(Restrictions.eq("email", email)));
    }
    
}
