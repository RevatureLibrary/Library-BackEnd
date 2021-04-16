package com.library.services;

import com.library.models.User;
import com.library.models.enums;
import com.library.models.request.UserDTO;
import com.library.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public void register(UserDTO user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User newU = new User(user);
        newU.setAccountType(enums.AccountType.PATRON);
        userRepo.save(newU);
    }

    public void save(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepo.save(user);
    }

    public void hire(UserDTO user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User newU = new User(user);
        newU.setAccountType(enums.AccountType.LIBRARIAN);
        userRepo.save(newU);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public List<User> readByAccountType(enums.AccountType librarian) {
        return userRepo.findByAccountType(librarian);
    }

    public User readByUsername(String userName) {
        return userRepo.findByUsername(userName);
    }

    public User readById(int parseInt) {
        return userRepo.findById(parseInt).get();
    }

    public void update(Fee fee, FeeDTO body) {

        if((body.getUsername()!=null) &&
                !body.getUsername().equals(u.getUsername())) u.setUsername(body.getUsername());

        if((body.getFirstName()!=null) &&
                !body.getFirstName().equals(u.getFirstName())) u.setFirstName(body.getFirstName());

        if((body.getLastName()!=null) &&
                !body.getLastName().equals(u.getLastName())) u.setLastName(body.getLastName());

        if((body.getEmail()!=null) &&
                !body.getEmail().equals(u.getEmail())) u.setEmail(body.getEmail());

        userRepo.save(u);

    }

    public void delete(int id) {
        userRepo.deleteById(id);
    }
}

