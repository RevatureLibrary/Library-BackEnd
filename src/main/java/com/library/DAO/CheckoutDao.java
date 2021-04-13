package com.library.DAO;

import com.library.models.Book;
import com.library.models.Checkout;
import com.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CheckoutDao extends JpaRepository<Checkout, Integer> {
    public Checkout getByBook(Book book);
   // @Query("SELECT * FROM Checkout co where user_id  = ?1")
    public List<Checkout> getByUser(User user);
    // Delete By Book or User Maybe?
}
