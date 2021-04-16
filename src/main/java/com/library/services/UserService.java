package com.library.services;

import com.library.DAO.UserDao;
import com.library.models.User;
import com.library.models.enums;
import com.library.models.request.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public void register(UserDTO user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User newU = new User(user);
        newU.setAccountType(enums.AccountType.PATRON);
        userDao.save(newU);
    }

    public void save(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.save(user);
    }

    public void hire(UserDTO user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User newU = new User(user);
        newU.setAccountType(enums.AccountType.LIBRARIAN);
        userDao.save(newU);
    }

    public List<User> getAll() {
        return userDao.findAll();
    }

    public List<User> readByAccountType(enums.AccountType librarian) {
        return userDao.findByAccountType(librarian);
    }

    public User readByUsername(String userName) {
        return userDao.findByUsername(userName);
    }

    public User readById(int parseInt) {
        return userDao.findById(parseInt).get();
    }

    public void update(User u, UserDTO body) {

        if((body.getUsername()!=null) &&
                !body.getUsername().equals(u.getUsername())) u.setUsername(body.getUsername());

        if((body.getFirstName()!=null) &&
                !body.getFirstName().equals(u.getFirstName())) u.setFirstName(body.getFirstName());

        if((body.getLastName()!=null) &&
                !body.getLastName().equals(u.getLastName())) u.setLastName(body.getLastName());

        if((body.getEmail()!=null) &&
                !body.getEmail().equals(u.getEmail())) u.setEmail(body.getEmail());

        userDao.save(u);

            }

    public void delete(int id) {
        userDao.deleteById(id);
    }
}
