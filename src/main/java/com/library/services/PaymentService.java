package com.library.services;


import com.library.models.Fee;
import com.library.models.Payment;
import com.library.models.enums;
import com.library.repo.FeeRepo;
import com.library.repo.PaymentDao;
import com.library.repo.UserRepo;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class PaymentService {
    @Autowired
    PaymentDao paymentDao;
    @Autowired
    FeeRepo feeRepo;
    @Autowired
    UserRepo userDao;

    public void makePayment(double amount, ArrayList<Integer> feeID, String userName){
        Payment paymentToBeMade = new Payment();
        paymentToBeMade.setAmount(amount);
        paymentToBeMade.setUser(userDao.findByUsername(userName));

        List<Fee> tempFeeSet = new ArrayList<>();
        for (int n : feeID){
            Fee temp = feeDao.findById(n);
            temp.setFeeStatus(enums.FeeStatus.PAID);
            temp.setResolved(new Timestamp(System.currentTimeMillis()));
            feeDao.save(temp);
            tempFeeSet.add(temp);
        }
        paymentToBeMade.setFeesPaid(tempFeeSet);
        paymentDao.save(paymentToBeMade);
        for (int n : feeID){
            Fee temp = feeDao.findById(n);
            temp.setPayment(paymentToBeMade);
            feeDao.save(temp);
        }
    }
}
