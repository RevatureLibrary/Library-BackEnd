package com.library.repo;

import com.library.models.Payment;
import com.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface PaymentDao extends JpaRepository<Payment, Integer> {
    //Return a list of payments specified by a userID
    List<Payment> findByUser_username(String username);
}
