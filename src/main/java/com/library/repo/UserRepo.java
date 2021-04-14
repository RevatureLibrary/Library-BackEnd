package com.library.repo;

import com.library.models.User;
import com.library.models.enums;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

    public interface UserRepo extends JpaRepository<User, Integer> {

        User findByUsername(String username);

        List<User> findByAccountType(enums.AccountType accountType);
    }

