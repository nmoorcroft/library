package com.zuhlke.library.user;

public class UserAdapter {

    private String email;
    private String name;
    
    public UserAdapter(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    
    public String getName() {
        return name;
    }
    
}
