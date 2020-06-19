package com.xuegao.springboot2_3_security.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class User implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authoritieList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoritieList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User() {
    }

    public User(String username, String password, List<GrantedAuthority> authoritieList) {
        this.username = username;
        this.password = password;
        this.authoritieList = authoritieList;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GrantedAuthority> getAuthoritieList() {
        return authoritieList;
    }

    public void setAuthoritieList(List<GrantedAuthority> authoritieList) {
        this.authoritieList = authoritieList;
    }
}