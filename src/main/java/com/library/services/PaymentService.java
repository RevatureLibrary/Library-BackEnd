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
        paymentToBeMade.setAmount(amount);//Todo:make sure this matches total of fee amounts
        paymentToBeMade.setUser(userRepo.findByUsername(userName));//Todo:make sure this is not null

        List<Fee> tempFeeSet = new ArrayList<>();
        for (int n : feeID){
            Fee temp = feeRepo.findById(n).get();//make sure its not already paid, exists, add the amounts up to check that
            temp.setFeeStatus(enums.FeeStatus.PAID);
            temp.setResolved(new Timestamp(System.currentTimeMillis()));
            feeRepo.save(temp);
            tempFeeSet.add(temp);
        }
        paymentToBeMade.setFeesPaid(tempFeeSet);
        System.out.println(paymentToBeMade);
        paymentRepo.save(paymentToBeMade);//check that this does not conflict
        for (int n : feeID){
            Fee temp = feeRepo.findById(n).get();
            temp.setPayment(paymentToBeMade);
            feeRepo.save(temp);//don't need this
        }
    }
}
