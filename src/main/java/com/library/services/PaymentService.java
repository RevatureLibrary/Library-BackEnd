package com.library.services;


import com.library.models.Fee;
import com.library.models.Payment;
import com.library.models.enums;
import com.library.repo.FeeRepo;
import com.library.repo.PaymentDao;
import com.library.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentService {
    @Autowired
    PaymentDao paymentDao;
    @Autowired
    FeeRepo feeRepo;
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userDao;

    public void makePayment(double amount, ArrayList<Fee> feeID, int userId){
        Payment paymentToBeMade = new Payment();
        paymentToBeMade.setAmount(amount);
        paymentToBeMade.setUser(userDao.getOne(userId));

        TreeSet<Fee> tempFeeSet = new TreeSet<>();
        for (Fee n : feeID){
            tempFeeSet.add(feeRepo.findById(n.getId()));
            Fee temp = feeRepo.findById(n.getId());
            temp.setFeeStatus(enums.FeeStatus.PAID);
        }
        paymentToBeMade.setFeesPaid(tempFeeSet);
        //paymentToBeMade.setUser(user);
        paymentDao.save(paymentToBeMade);
    }

    public List<Payment> getAllPaymentsMadeByUser(String username){return paymentDao.findByUser_username(username);}

    public Payment getPaymentById(int paymentId){
        return paymentDao.getOne(paymentId);
    }
}
