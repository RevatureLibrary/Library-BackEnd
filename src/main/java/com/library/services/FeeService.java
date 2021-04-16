package com.library.services;

import com.library.DAO.FeeDao;
import com.library.models.Fee;
import com.library.models.User;
import com.library.models.enums;
import com.library.models.request.FeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
services: look at fees for users, create new fee, edit fee, delete fee.
 */

@Service
public class FeeService {
    @Autowired
    FeeDao feeDao;

    public List<Fee> readByFeeStatus(enums.FeeStatus feeStatus) {
        return feeDao.findByFeeStatus(feeStatus);
    }

    public List<Fee> readByFeeType(enums.FeeType feeType) {
        return feeDao.findByFeeType(feeType);
    }

    public List<Fee> getAll() {
        return feeDao.findAll();
    }

    public Fee readByUsername(String userName) {
        return feeDao.findByUsername(userName);
    }

    public void delete(int id) {
        feeDao.deleteById(id);
    }

    public void update(Fee fee, FeeDTO body) {
        if ((body.getAssessed() != null) &&
                !body.getAssessed().equals(fee.getAssessed())) fee.setAssessed(body.getAssessed());

        if ((body.getResolved() != null) &&
                !body.getResolved().equals(fee.getResolved())) fee.setAmount(body.getAmount());

        if ((body.getAmount() != 0.0) &&
                !(body.getAmount() == (fee.getAmount()))) fee.setAmount(body.getAmount());

        if ((body.getFeeStatus() != null) &&
                !body.getFeeStatus().equals(fee.getFeeStatus())) fee.setFeeStatus(body.getFeeStatus());

        if ((body.getAmount() != 0.0) &&
                !(body.getFeeType() == (fee.getFeeType()))) fee.setFeeType(body.getFeeType());
        feeDao.save(fee);
    }
}
