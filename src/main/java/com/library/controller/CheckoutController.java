package com.library.controller;

import com.library.models.*;
import com.library.repo.BookRepo;
import com.library.models.dto.CheckoutDTO;
import com.library.services.BookService;
import com.library.services.CheckoutService;

import static com.library.util.AuthorityUtil.*;

import com.library.services.FeeService;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = {"**/checkouts","/library/checkouts"}, consumes = "application/json", produces = "application/json")
public class CheckoutController {
    @Autowired
    CheckoutService checkoutService;
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    // works
    @GetMapping
    public ResponseEntity<?> getAllCheckouts(){
        // You can retrieve the username, password, etc.
        // from the SecurityContextHolder by casting to JWTUserDetails
        //        SecurityContextHolder.getContext()
        if(isEmployee() || isAdmin())
            return ResponseEntity.ok(checkoutService.getAll());
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping(path="/username={username}")
    public @ResponseBody
    ResponseEntity<?> getByUsername(@PathVariable String username){
        if(isPatron() || isEmployee() || isAdmin()){
            User u = userService.readByUsername(username);
            if(u == null){
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(checkoutService.getByUser(u), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    // This grabs by a certain Checkout Id
    @GetMapping(path="/{id}")
    public @ResponseBody
    ResponseEntity<Checkout> getById(@PathVariable String id){
        Checkout checkout = checkoutService.getById(Integer.parseInt(id));
        if(checkout == null)
            return ResponseEntity.badRequest().build();
        if(isPatron() || isEmployee() || isAdmin())
            return ResponseEntity.ok(checkoutService.getById(Integer.parseInt(id)));

        return ResponseEntity.badRequest().build();
    }


    // works
    @PostMapping()
    public ResponseEntity<Checkout> insertCheckout(@RequestBody CheckoutDTO checkoutDTO){
        Book book = bookService.getByBookId(checkoutDTO.getBookId());
        User user = userService.readByUsername(checkoutDTO.getUsername());
        if(checkoutDTO == null || book == null || user == null || book.getBookStatus() != enums.BookStatus.AVAILABLE){
            return ResponseEntity.notFound().build();
        }
        if(isPatron() || isEmployee() || isAdmin()){
            Checkout checkout = new Checkout(checkoutDTO);
            checkout.setBook(book);
            checkout.setUser(user);
            checkout = checkoutService.checkoutBook(checkout);
            System.out.println(checkout);
            return new ResponseEntity<>(checkout, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    // This is for a User returning a book
    // TODO: Consider changing path
    @PutMapping(path="/{id}")
    public ResponseEntity<Checkout> updateCheckout(@PathVariable String id){
        Checkout checkout = checkoutService.getById(Integer.parseInt(id));
        if(checkout == null){
            return ResponseEntity.notFound().build();
        }
        if(isPatron() || isEmployee() || isAdmin()){
            checkout = checkoutService.returnCheckout(checkout);
            return new ResponseEntity<>(checkout, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    // TODO: Consider changing the path
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Checkout> deleteById(@PathVariable String id){
        Checkout checkout = checkoutService.getById(Integer.parseInt(id));
        if(checkout == null){
            return ResponseEntity.notFound().build();
        }
        if(isEmployee() || isPatron() || isAdmin()){
            checkoutService.deleteById(Integer.parseInt(id));
            return new ResponseEntity<>(checkout, HttpStatus.OK);
        }

        return ResponseEntity.badRequest().build();
    }

}
