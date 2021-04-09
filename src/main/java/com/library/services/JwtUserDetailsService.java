package com.library.services;

import com.library.models.request.JWTUserDetails;
import com.library.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User read = null;
        try {
            read = userService.readByUsername(userName);
        } catch (Exception e) {
            System.out.println("username or password not found");
            throw new UsernameNotFoundException("username or password not found");
        }
        return new JWTUserDetails(read.getUsername(),read.getPassword(), read.getAccountType());
    }
}
