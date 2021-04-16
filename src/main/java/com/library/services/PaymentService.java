package com.library.services;


import com.library.DAO.FeeDao;
import com.library.DAO.PaymentDao;
import com.library.DAO.UserDao;
import com.library.models.Fee;
import com.library.models.Payment;
import com.library.models.User;
import com.library.models.enums;
import com.library.models.request.PaymentDTO;
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
    UserService userService;
    @Autowired
    UserDao userDao;

    public void makePayment(double amount, ArrayList<Fee> feeID, int userId){
        Payment paymentToBeMade = new Payment();
        paymentToBeMade.setAmount(amount);
        paymentToBeMade.setUser(userDao.getOne(userId));

        TreeSet<Fee> tempFeeSet = new TreeSet<>();
        for (Fee n : feeID){
            tempFeeSet.add(feeDao.findById(n.getId()));
            Fee temp = feeDao.findById(n.getId());
            temp.setFeeStatus(enums.FeeStatus.PAID);
        }
        paymentToBeMade.setFeesPaid(tempFeeSet);
        //paymentToBeMade.setUser(user);
        paymentDao.save(paymentToBeMade);
    }

    public List<Payment> getAllPaymentsMadeByUser(int userId){return paymentDao.findByUser(userId);}

    public Payment getPaymentById(int paymentId){
        return paymentDao.getOne(paymentId);
    }
}
