package com.library.services;

import com.library.models.*;
import com.library.repo.BookRepo;
import com.library.repo.CheckoutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckoutService {
    @Autowired
    CheckoutRepo checkoutRepo;
    @Autowired
    BookRepo bookRepo;

    public List<Checkout> getAll(){
        return checkoutRepo.findAll();
    }

    public Checkout getById(Integer id){
        return checkoutRepo.findById(id).get();
    }

    public Checkout getByBook(Book book){
        return checkoutRepo.getByBook(book);
    }

    public List<Checkout> getByUser(User user){
        return checkoutRepo.getByUser(user);
    }

    public Checkout checkoutBook(Checkout checkout){
        Book book = bookRepo.getById(checkout.getBook().getId());
        book.setBookStatus(enums.BookStatus.CHECKED_OUT);
        bookRepo.save(book);
        checkoutRepo.save(checkout);
        return checkout;
    }

    public void save(Checkout checkout){ checkoutRepo.save(checkout); }

    public void delete(Checkout checkout){
        checkoutRepo.delete(checkout);
    }

    public void deleteById(Integer id){
        checkoutRepo.deleteById(id);
    }

    public Checkout returnCheckout(Checkout checkout){
        //
        Book book = checkout.getBook();
        checkout.setCheckoutStatus(enums.CheckoutStatus.RETURNED);
        Timestamp dateReturned = Timestamp.valueOf(LocalDateTime.now());
        if(dateReturned.after(checkout.getReturnDueDate())){
            Fee fee = new Fee(0, dateReturned, null, 2.50d, enums.FeeType.LATE, enums.FeeStatus.UNPAID, checkout.getUser(), null);
            checkout.setFee(fee);
        }
        checkout.getBook().setBookStatus(enums.BookStatus.OFF_SHELF);
        bookRepo.save(book);
        checkoutRepo.save(checkout);
        return checkout;
    }

}
