package com.library.controler;

import com.library.models.Fee;
import com.library.models.Payment;
import com.library.models.User;
import com.library.models.request.PaymentDTO;
import com.library.models.request.UserDTO;
import com.library.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static com.library.util.AuthorityUtil.getAuth;
import static com.library.util.AuthorityUtil.isEmployee;

@RestController
@RequestMapping(value = "/payment",consumes = "application/json", produces = "application/json")
public class PaymentController {
    @Autowired
    PaymentService paymentService;


    @PostMapping()
    public ResponseEntity<Payment> register(@RequestBody PaymentDTO payment) throws Exception{
        if(payment.getAmount()==0 ||payment.getFeesPaid()==null)
            return ResponseEntity.unprocessableEntity().build();

        else {
            paymentService.makePayment(payment.getAmount(), payment.getFeesPaid(), payment.getUserId());
        }
        return ResponseEntity.status(201).body(paymentService.getPaymentById(payment.getId()));
    }
}
