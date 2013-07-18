package com.zuhlke.library.user;

import com.zuhlke.library.domain.User;
import com.zuhlke.library.domain.UserRole;

public class UserAdapter {

    private String email;
    private String name;
    private UserRole role;
    
    public UserAdapter(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.role = user.getRole();
    }

    public String getEmail() {
        return email;
    }
    
    public String getName() {
        return name;
    }
    
    public UserRole getRole() {
        return role;
    }
    
}
