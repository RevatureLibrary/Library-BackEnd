package com.library.controler;

import com.library.models.User;
import com.library.models.enums;
import com.library.models.request.JWTUserDetails;
import com.library.models.request.UserDTO;
import com.library.services.UserService;
import com.library.util.AuthorityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.library.util.AuthorityUtil.getAuth;
import static com.library.util.AuthorityUtil.isEmployee;

@RestController
@RequestMapping(value = "/users",consumes = "application/json", produces = "application/json")
public class UserController {

    @Autowired
    UserService userService;

    private String getRequestUsername(){
        return ((JWTUserDetails) (SecurityContextHolder.getContext())).getUsername();}


    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        if(AuthorityUtil.isEmployee(getAuth()))
            return ResponseEntity.ok(userService.getAll());
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<User>
    getById(@PathVariable String id) {
        User user = userService.readById(Integer.parseInt(id));
        if (user == null)
            return ResponseEntity.badRequest().build();

        if (isEmployee(getAuth())||
                getRequestUsername().equals(user.getUsername()))
        return new ResponseEntity<>(user, HttpStatus.OK);

        return ResponseEntity.badRequest().build();
    }
    @GetMapping(path="/username={username}")
    public @ResponseBody ResponseEntity<User>
    getByUsername(@PathVariable String username) {
        User user = userService.readByUsername(username);
        if (user == null)
            return ResponseEntity.badRequest().build();

        if (isEmployee(getAuth())||
                getRequestUsername().equals(user.getUsername()))
            return new ResponseEntity<>(user, HttpStatus.OK);

        return ResponseEntity.badRequest().build();
    }
    @PostMapping()
    public ResponseEntity<User> register(@RequestBody UserDTO user) throws Exception{
        System.out.println(getAuth());

        System.out.println(user);
        if(user.getUsername()==null ||user.getPassword()==null)
            return ResponseEntity.unprocessableEntity().build();
        if (user.isHire() && isEmployee(getAuth()))
            userService.hire(user);
        else
            userService.register(user);
        return ResponseEntity.status(201).body(userService.readByUsername(user.getUsername()));
    }






}
