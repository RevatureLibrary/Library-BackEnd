package com.library.DAO;

import com.library.models.User;
import com.library.models.enums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    List<User> findByAccountType(enums.AccountType accountType);
}
