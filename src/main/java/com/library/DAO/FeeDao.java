package com.library.DAO;

import com.library.models.Fee;
import com.library.models.User;
import com.library.models.enums;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeDao extends JpaRepository<User, Integer> {

    List<Fee> findByFeeStatus(enums.FeeStatus feeStatus);

    List<Fee> findByFeeType(enums.FeeType feeType);
}
