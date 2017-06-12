package com.youbi.app.core.security;

import com.youbi.app.model.User;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Account implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(User.class);

    private User user;

    private final List<GrantedAuthority> authorities = new ArrayList<>();

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return user.getHash();
    }

    public String getUsername() {
        return user.getUserName();
    }

    public boolean isAccountNonExpired() {
        return false;
    }

    public boolean isAccountNonLocked() {
        return false;
    }

    public boolean isCredentialsNonExpired() {
        return false;
    }

    public boolean isEnabled() {
        return true;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
        setUserAuthorities();
    }

    private void setUserAuthorities(){
        if(getUser() != null){
            getAuthorities().add(new SimpleGrantedAuthority(getUser().getRole().getRoleName()));
        }
    }
}
