package com.library.controller;
import com.library.models.Payment;
import com.library.models.dto.PaymentDTO;
import com.library.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment",consumes = "application/json", produces = "application/json")
public class PaymentController {
    @Autowired
    PaymentService paymentService;


    @PostMapping()
    public ResponseEntity<String> register(@RequestBody PaymentDTO payment) throws Exception{
        System.out.println(payment);
        if(payment.getAmount()==0 ||payment.getFeesPaid()==null)
            return ResponseEntity.unprocessableEntity().build();

        else {
            paymentService.makePayment(payment.getAmount(), payment.getFeesPaid(), payment.getUsername());
            return ResponseEntity.status(201).body("okay");

        }
    }


}
