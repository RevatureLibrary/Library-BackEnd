package com.library.services;

import com.library.models.dto.CheckoutDTO;
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
    CheckoutRepo checkoutDao;

    public List<Checkout> getAll(){
        return checkoutDao.findAll();
    }

    public Checkout getById(Integer id){
        return checkoutDao.findById(id).get();
    }

    public Checkout getByBook(Book book){
        return checkoutDao.getByBook(book);
    }

    public List<Checkout> getByUser(User user){
        return checkoutDao.getByUser(user);
    }

    public void checkoutBook(Checkout checkout){
        checkoutDao.save(checkout);
    }

    public void save(Checkout checkout){ checkoutDao.save(checkout); }

    public void delete(Checkout checkout){
        checkoutDao.delete(checkout);
    }

    public void deleteById(Integer id){
        checkoutDao.deleteById(id);
    }

    public void returnCheckout(Checkout checkout){
        //
        checkoutDao.save(checkout);
    }

}
