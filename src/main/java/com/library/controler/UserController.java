package com.library.controler;

import com.library.models.User;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllUsers(){
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))||
                SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))
            return ResponseEntity.ok(userService.getAll());
        else
            return ResponseEntity.badRequest().build();
    }
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> register(@RequestBody User user) throws Exception{
        userService.register(user);
        return ResponseEntity.status(201).body(userService.readByUsername(user.getUsername()));
    }




}
