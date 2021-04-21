package com.library.repo;

import com.library.models.Fee;
import com.library.models.User;
import com.library.models.enums;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeRepo extends JpaRepository<Fee, Integer> {

    List<Fee> findByFeeStatus(enums.FeeStatus feeStatus);

    List<Fee> findByFeeType(enums.FeeType feeType);
    List<Fee> findByUser_username(String userName);

    Fee findById(int id);
}
