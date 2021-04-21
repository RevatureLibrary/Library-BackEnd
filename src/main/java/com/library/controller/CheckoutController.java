package com.library.controller;

import com.library.models.*;
import com.library.models.dto.CheckoutDTO;
import com.library.services.BookService;
import com.library.services.CheckoutService;

import static com.library.util.AuthorityUtil.*;

import com.library.services.FeeService;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/library/checkouts", consumes = "application/json", produces = "application/json")
public class CheckoutController {
    @Autowired
    CheckoutService checkoutService;
    @Autowired
    FeeService feeService;
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

    // works
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

        Checkout checkout = new Checkout(checkoutDTO);
        Book book = bookService.getByBookId(checkoutDTO.getBookId());
        User user = userService.readByUsername(checkoutDTO.getUsername());
        if(book == null || book.getBookStatus() != enums.BookStatus.AVAILABLE || user == null){
            return ResponseEntity.unprocessableEntity().build();
        }

        if(isPatron() || isEmployee() || isAdmin()){
            checkout.setUser(user);
            checkout.setBook(book);
            book.setBookStatus(enums.BookStatus.CHECKED_OUT);
//            bookService.update(book);
            // Might need to create Fee for checkout not sure
            checkoutService.checkoutBook(checkout);
        }
        return new ResponseEntity<>(checkout, HttpStatus.OK);
    }

    // This is for a User returning a book
    // TODO: Consider changing path
    @PutMapping(path="/{id}")
    public ResponseEntity<Checkout> updateCheckout(@PathVariable String id){

        Checkout checkout = checkoutService.getById(Integer.parseInt(id));
        if(checkout == null){
            return ResponseEntity.notFound().build();
        }
        checkout.setCheckoutStatus(enums.CheckoutStatus.RETURNED);
        Timestamp dateReturned = Timestamp.valueOf(LocalDateTime.now());
        if(dateReturned.after(checkout.getReturnDueDate())){
            Fee fee = new Fee(0, dateReturned, null, 2.50d, enums.FeeType.LATE, enums.FeeStatus.UNPAID, checkout.getUser(), null);
            checkout.setFee(fee);
        }
        checkout.getBook().setBookStatus(enums.BookStatus.OFF_SHELF);
        checkoutService.returnCheckout(checkout);
        return new ResponseEntity<>(checkout, HttpStatus.OK);
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
