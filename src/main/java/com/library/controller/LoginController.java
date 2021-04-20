package com.library.controller;

import com.library.models.User;
import com.library.models.request.LoginAttempt;
import com.library.models.response.LoginResponse;
import com.library.services.UserService;
import com.library.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.library.util.AuthorityUtil.getAuth;


@RestController @CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    @Autowired
    UserService userService;


    @PostMapping(value = "/library/authentication")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginAttempt loginAttempt) throws Exception{
        LoginResponse login = null;
        System.out.println(loginAttempt);
        System.out.println(getAuth());
        try {
            login = userService.login(loginAttempt);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(login);
    }
}



