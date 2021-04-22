package com.library.services;

import com.library.models.enums;
import com.library.repo.BookRepo;
import com.library.repo.CheckoutRepo;
import com.library.models.Book;
import com.library.models.Checkout;
import com.library.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void returnCheckout(Checkout checkout){
        //
        checkoutRepo.save(checkout);
    }

}
