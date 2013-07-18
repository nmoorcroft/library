package com.zuhlke.library.user;

import com.zuhlke.library.domain.User;

public interface UserService {

    User findByEmail(String email);
    void createUser(User user);

}
