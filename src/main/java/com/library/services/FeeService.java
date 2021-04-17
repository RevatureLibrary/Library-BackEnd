package com.library.services;

import com.library.models.Fee;
import com.library.models.enums;
import com.library.repo.FeeRepo;
import com.library.models.request.FeeDTO;
import com.library.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeService {
    @Autowired
    FeeRepo feeRepo;

    public List<Fee> getAll() {
        return feeRepo.findAll();
    }

    public List<Fee> readByFeeStatus(enums.FeeStatus feeStatus) {
        return feeRepo.findByFeeStatus(feeStatus);
    }

    public List<Fee> readByFeeType(enums.FeeType feeType) {
        return feeRepo.findByFeeType(feeType);
    }

    public List<Fee> getByUserName(String name) {
        return feeRepo.findByUsername(name);
    }

    public void update(Fee fee, FeeDTO body) {

        if((body.getAssessed()!=null) &&
                !body.getAssessed().equals(fee.getAssessed())) fee.setAssessed(body.getAssessed());

        if((body.getResolved()!=null) &&
                !body.getResolved().equals(fee.getResolved())) fee.setResolved(body.getResolved());

        if((body.getAmount()!=0.0) &&
                body.getAmount() != fee.getAmount()) fee.setAmount(body.getAmount());

        if((body.getFeeStatus()!=null) &&
                !body.getFeeStatus().equals(fee.getFeeStatus())) fee.setFeeStatus(body.getFeeStatus());

        if((body.getFeeType()!=null) &&
                !body.getFeeType().equals(fee.getFeeType())) fee.setFeeType(body.getFeeType());

        feeRepo.save(fee);

    }

    public void delete(int id) {
        feeRepo.deleteById(id);
    }
}

