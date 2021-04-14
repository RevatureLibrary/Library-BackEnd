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
import org.springframework.web.bind.annotation.*;

import static com.library.util.AuthorityUtil.*;

@RestController
@RequestMapping(value = {"library/users","**/users"},consumes = "application/json", produces = "application/json")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        if(AuthorityUtil.isEmployee())
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

        if (isEmployee()||
                getRequesterUsername().equals(user.getUsername()))
        return new ResponseEntity<>(user, HttpStatus.OK);

        return ResponseEntity.badRequest().build();
    }
    @GetMapping(path="/username={username}")
    public @ResponseBody ResponseEntity<User>
    getByUsername(@PathVariable String username) {
        User user = userService.readByUsername(username);
        if (user == null)
            return ResponseEntity.badRequest().build();

        if (isEmployee()||
                getRequesterUsername().equals(user.getUsername()))
            return new ResponseEntity<>(user, HttpStatus.OK);

        return ResponseEntity.badRequest().build();
    }
    @GetMapping(path="/librarians={librarian}")
    public @ResponseBody ResponseEntity
    getByIsLibrarian(@PathVariable String librarian) {
        boolean isLibrarian = Boolean.parseBoolean(librarian);
        if(isLibrarian)
            return new ResponseEntity(userService.readByAccountType(enums.AccountType.LIBRARIAN),HttpStatus.OK);
        else if(isEmployee())
            return new ResponseEntity(userService.readByAccountType(enums.AccountType.PATRON),HttpStatus.OK);
        else
        return ResponseEntity.badRequest().build();
    }


    @PostMapping()
    public ResponseEntity<User> register(@RequestBody UserDTO user) throws Exception{
        if(user.getUsername()==null ||user.getPassword()==null)
            return ResponseEntity.unprocessableEntity().build();
        if (user.isHire() && isEmployee())
            userService.hire(user);
        else
            userService.register(user);
        return ResponseEntity.status(201).body(userService.readByUsername(user.getUsername()));
    }

    @PutMapping(path="/{id}")
    public @ResponseBody ResponseEntity<User>
    updateById(@PathVariable int id, @RequestBody UserDTO body) {
        User user = userService.readById(id);
        if (user == null)
            return ResponseEntity.notFound().build();

        if (isEmployee())
        {
            userService.update(user,body);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping(path="/{id}")
    public @ResponseBody ResponseEntity<User>
    deleteById(@PathVariable int id) {
        User user = userService.readById(id);
        if (user == null)
            return ResponseEntity.notFound().build();

        if (isEmployee() || //if DELETE request is being made by employee OR
                (isPatron()&& //DELETE request is being made by patron AND
                        user.getUsername().equals(getRequesterUsername())))// patron is attempting to DELETE self
                {
            userService.delete(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return ResponseEntity.badRequest().build();
    }






}
