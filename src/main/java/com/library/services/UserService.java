package com.library.services;

import com.library.models.User;
import com.library.models.enums;
import com.library.models.dto.LoginAttemptDTO;
import com.library.models.dto.UserDTO;
import com.library.models.dto.LoginResponseDTO;
import com.library.repo.UserRepo;
import com.library.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo,PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;

    }

    public void register(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newU = new User(
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()

        );
        newU.setAccountType(enums.AccountType.PATRON);
        userRepo.save(newU);
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public void hire(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newU = new User(
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()

        );
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

    public void update(User u, UserDTO body) {

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

    public List<User> searchByUsername(String username) {
      return userRepo.findByUsernameContainsIgnoreCase(username);
    }

    public LoginResponseDTO login(LoginAttemptDTO loginAttemptDTO) {
        User activeUser = readByUsername(loginAttemptDTO.getUsername());
        if
        (
                activeUser == null ||
                        !passwordEncoder.matches(loginAttemptDTO.getPassword(),activeUser.getPassword())
        )
            throw new BadCredentialsException("Invalid username or password");
        else
            return createSuccessfulLoginResponse(activeUser);

    }

    public LoginResponseDTO createSuccessfulLoginResponse(User user) {
        return new LoginResponseDTO(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                JWTUtil.generateToken(user),
                user.getAccountType().toString()
        );

    }

}
