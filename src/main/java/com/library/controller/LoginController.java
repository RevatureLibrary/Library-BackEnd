package com.library.controller;

import com.library.models.dto.LoginAttemptDTO;
import com.library.models.dto.LoginResponseDTO;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import static com.library.util.AuthorityUtil.getAuth;


@RestController @CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    @Autowired
    UserService userService;


    @PostMapping(value = "/library/authentication")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginAttemptDTO loginAttemptDTO) throws Exception{
        LoginResponseDTO login = null;
        System.out.println(loginAttemptDTO);
        System.out.println(getAuth());
        try {
            login = userService.login(loginAttemptDTO);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(login);
    }
}



