package com.library.controller;

import com.library.models.Book;
import com.library.models.Checkout;
import com.library.models.User;
import com.library.models.request.CheckoutDTO;
import com.library.services.BookService;
import com.library.services.CheckoutService;

import static com.library.util.AuthorityUtil.*;

import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/library/checkouts", consumes = "application/json", produces = "application/json")
public class CheckoutController {
    @Autowired
    CheckoutService checkoutService;
    // @Autowired
    // FeeService feeService;
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

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


    @PostMapping()
    public ResponseEntity<Checkout> insertCheckout(@RequestBody CheckoutDTO checkoutDTO){

        Checkout checkout = new Checkout(checkoutDTO);
//        Book book = bookService.getById(checkoutDTO.getBookId());
        User user = userService.readByUsername(checkoutDTO.getUsername());
//        if(book == null || book.getBookStatus() != enums.BookStatus.AVAILABLE || user == null){
//            return ResponseEntity.unprocessableEntity().build();
//        }
        checkout.setUser(user);
//        checkout.setBook(book);

        if(isPatron() || isEmployee() || isAdmin()){
            checkoutService.checkoutBook(checkout);
        }
        return new ResponseEntity<>(checkout, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Checkout> updateCheckout(@RequestBody Checkout checkout){
        Integer id = checkout.getId();
        if(checkoutService.getById(id) == null){
            return ResponseEntity.notFound().build();
        }

    }

    //TODO: Consider changing the path
    @DeleteMapping()
    public ResponseEntity<?> deleteCheckout(@RequestBody Checkout checkout){

        if(checkout == null){
            return ResponseEntity.unprocessableEntity().build();
        }
        if(isEmployee() || isAdmin()){
            checkoutService.delete(checkout);
        }
        return new ResponseEntity<>(checkout, HttpStatus.OK);
    }

    // TODO: Consider changing the path
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Checkout> deleteById(@PathVariable int id){
        Checkout checkout = checkoutService.getById(id);
        if(checkout == null){
            return ResponseEntity.notFound().build();
        }
        if(isEmployee() || isPatron() || isAdmin()){
            checkoutService.deleteById(id);
            return new ResponseEntity<>(checkout, HttpStatus.OK);
        }

        return ResponseEntity.badRequest().build();
    }

}
