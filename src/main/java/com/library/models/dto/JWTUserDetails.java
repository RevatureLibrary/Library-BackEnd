package com.library.models.dto;

import com.library.models.enums;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class JWTUserDetails implements UserDetails {


    private String username;
    private String password;
    private enums.AccountType accountType;

    public JWTUserDetails(String username, String password, enums.AccountType accountType) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (accountType.toAuth());
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
}
