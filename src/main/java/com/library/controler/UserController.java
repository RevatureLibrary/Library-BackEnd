package com.library.controler;

import com.library.models.User;
import com.library.models.enums;
import com.library.models.request.UserDTO;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    SimpleGrantedAuthority authority;

    @Autowired
    UserService userService;

    private SimpleGrantedAuthority getAuth()
    {
        return (SimpleGrantedAuthority) SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().findFirst().get();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllUsers(){
        authority= getAuth();
        if(authority != enums.AccountType.PATRON.toAuth())
            return ResponseEntity.ok(userService.getAll());
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/users/{id}")
    public @ResponseBody ResponseEntity<String>
    getById(@PathVariable String id) {
        return new ResponseEntity<String>("GET Response : "
                + id, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> register(@RequestBody UserDTO user) throws Exception{

        if(user.getUsername()==null ||user.getPassword()==null)
            return ResponseEntity.unprocessableEntity().build();
        if (user.isHire() &&
                authority!= enums.AccountType.PATRON.toAuth())
            userService.hire(user);
        else
            userService.register(user);
        return ResponseEntity.status(201).body(userService.readByUsername(user.getUsername()));
    }






}
