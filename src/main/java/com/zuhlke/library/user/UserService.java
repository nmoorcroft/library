package com.zuhlke.library.user;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zuhlke.library.domain.User;
import com.zuhlke.library.domain.UserRole;
import com.zuhlke.library.repositories.UserRepository;

@Service
public class UserService {

    @Inject
    private UserRepository userRepository;
    
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Transactional
    public void createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new DuplicateEmailException();
        }
        user.setRole(UserRole.USER);
        userRepository.save(user);
    }
    
}

