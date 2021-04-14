package com.library.services;

import com.library.DAO.PaymentDao;
import com.library.DAO.UserDao;
import com.library.models.Fee;
import com.library.models.Payment;
import com.library.models.request.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PaymentService {
    @Autowired
    PaymentDao paymentDao;
    UserService userService;

    public void makePayment(double amount, ArrayList<Integer> feeID, int userId){
        Payment paymentToBeMade = new Payment();
        paymentToBeMade.setAmount(amount);

        HashSet<Fee> tempFeeSet = new HashSet();
        for (int n : feeID){
            //TODO: use the FeeDao to fetch fee objects from the fee table using the set of feeId integers provided in
            // the parameter of the method and add it to the tempFeeSet object
        }
        paymentToBeMade.setFeesPaid(tempFeeSet);
        paymentToBeMade.setUser(userService.readById(userId));
    }

    public List<Payment> getAllPaymentsMadeByUser(int userId){return paymentDao.paymentsMadeByUser(userId);}
}
