package com.library.controller;

import com.library.models.Fee;
import com.library.models.dto.FeeDTO;
import com.library.services.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.library.util.AuthorityUtil.isEmployee;

@RestController
@RequestMapping(value = "/fees",consumes = "application/json", produces = "application/json")
public class FeeController {
    @Autowired
    FeeService feeService;

    @GetMapping
    public ResponseEntity<?> getAllFees(){
        if(isEmployee())
            return ResponseEntity.ok(feeService.getAll());
        else
            return ResponseEntity.status(403).build();

    }

    @PostMapping
    public ResponseEntity postFee(@RequestBody Fee fee){
        FeeDTO temp = new FeeDTO(fee.getAssessed(), fee.getResolved(), fee.getAmount(), fee.getFeeStatus(), fee.getFeeType());
        if(isEmployee()) {
            feeService.update(fee, temp);
            return ResponseEntity.status(HttpStatus.CREATED).body(temp);
        }
        else
            return ResponseEntity.status(403).build();
    }
}
