package com.library.services;

import com.library.DAO.UserDao;
import com.library.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User readByUsername(String userName) {
    return userDao.findByUsername(userName);
    }

    public List<User> getAll() {
        return userDao.findAll();
    }

    public void register(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.save(user);
    }
    public void save(User user){
        userDao.save(user);
    }
}
