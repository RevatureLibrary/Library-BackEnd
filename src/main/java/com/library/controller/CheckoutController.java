package com.library.controller;

import com.library.models.Checkout;
import com.library.services.CheckoutService;
import com.library.util.AuthorityUtil;
import static com.library.util.AuthorityUtil.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/checkouts", consumes = "application/json", produces = "application/json")
public class CheckoutController {
    @Autowired
    CheckoutService checkoutService;

    @GetMapping
    public ResponseEntity<?> getAllCheckouts(){
        // You can retrieve the username, password, etc.
        // from the SecurityContextHolder by casting to JWTUserDetails
//        SecurityContextHolder.getContext()
        if(isEmployee())
            return ResponseEntity.ok(checkoutService.getAll());
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody
    ResponseEntity<Checkout> getById(@PathVariable String id){
        Checkout checkout = checkoutService.getById(Integer.parseInt(id));
        if(checkout == null)
            return ResponseEntity.badRequest().build();
        if(isPatron())
            return new ResponseEntity<>(checkout, HttpStatus.OK);

        return ResponseEntity.badRequest().build();
    }


}
