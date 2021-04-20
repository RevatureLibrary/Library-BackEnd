package com.library.services;


import com.library.models.Fee;
import com.library.models.Payment;
import com.library.models.User;
import com.library.models.enums;
import com.library.models.request.PaymentDTO;
import com.library.repo.FeeDao;
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
    FeeDao feeDao;
    @Autowired
    UserRepo userDao;

    public void makePayment(double amount, ArrayList<Integer> feeID, int userId){
        Payment paymentToBeMade = new Payment();
        paymentToBeMade.setAmount(amount);
        paymentToBeMade.setUser(userDao.getOne(userId));

        TreeSet<Fee> tempFeeSet = new TreeSet<>();
        for (int n : feeID){
            Fee temp = feeDao.getOne(n);
            temp.setFeeStatus(enums.FeeStatus.PAID);
            feeDao.save(temp);
            tempFeeSet.add(temp);
        }
        paymentToBeMade.setFeesPaid(tempFeeSet);
        paymentDao.save(paymentToBeMade);
    }
}
