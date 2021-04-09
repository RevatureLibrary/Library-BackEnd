package com.library.DAO;

import com.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
