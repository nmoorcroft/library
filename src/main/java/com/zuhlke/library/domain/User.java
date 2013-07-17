package com.zuhlke.library.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.google.common.base.Objects;

@Entity @Table(name = "library_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User implements Serializable {

    private static final long serialVersionUID = 594893770324699816L;

    @Id 
    @GeneratedValue(generator = "user_id", strategy = GenerationType.TABLE)
    @TableGenerator(name = "user_id", pkColumnValue = "user")
    @Column(name = "user_id")    
    private Long id;
    
    @Column(name = "email")
    @Length(max = 100)
    @Basic(optional = false)
    private String email;
    
    @Column(name = "password")
    @Length(max = 100)
    @Basic(optional = false)
    private String password;
    
    @Column(name = "fullname")
    @Length(max = 250)
    @Basic(optional = false)
    private String name;
    
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private UserRole role;
    
    User() { }
    
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public Long getId() {
        return id;
    }
    
    protected void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public UserRole getRole() {
        return role;
    }
    
    public void setRole(UserRole role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).toString();
    }
    
}
