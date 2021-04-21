package com.library.repo;

import com.library.models.Payment;
import com.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

}
