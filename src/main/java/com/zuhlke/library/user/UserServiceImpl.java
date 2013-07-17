package com.zuhlke.library.user;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.zuhlke.library.domain.User;
import com.zuhlke.library.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;
    
    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
}

