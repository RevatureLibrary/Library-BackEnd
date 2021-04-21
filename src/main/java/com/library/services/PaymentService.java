package com.library.services;


import com.library.models.Fee;
import com.library.models.Payment;
import com.library.models.enums;
import com.library.repo.FeeRepo;
import com.library.repo.PaymentRepo;
import com.library.repo.UserRepo;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class PaymentService {
    @Autowired
    PaymentRepo paymentRepo;
    @Autowired
    FeeRepo feeRepo;
    @Autowired
    UserRepo userRepo;

    public void makePayment(double amount, ArrayList<Integer> feeID, String userName){
        Payment paymentToBeMade = new Payment();
        paymentToBeMade.setAmount(amount);
        paymentToBeMade.setUser(userRepo.findByUsername(userName));

        List<Fee> tempFeeSet = new ArrayList<>();
        for (int n : feeID){
            Fee temp = feeRepo.findById(n);
            temp.setFeeStatus(enums.FeeStatus.PAID);
            temp.setResolved(new Timestamp(System.currentTimeMillis()));
            feeRepo.save(temp);
            tempFeeSet.add(temp);
        }
        paymentToBeMade.setFeesPaid(tempFeeSet);
        paymentRepo.save(paymentToBeMade);
        for (int n : feeID){
            Fee temp = feeRepo.findById(n);
            temp.setPayment(paymentToBeMade);
            feeRepo.save(temp);
        }
    }
}
