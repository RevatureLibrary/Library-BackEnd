package com.library.models;

import com.library.models.request.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Email
    @Column
    private String email;

    @Column
    private Timestamp created = new Timestamp(System.currentTimeMillis());
    @Enumerated
    private enums.AccountType accountType = enums.AccountType.PATRON;


    public User (UserDTO regDTO){
        this.username = regDTO.getUsername();
        this.password = regDTO.getPassword();
        this.firstName = regDTO.getFirstName();
        this.lastName = regDTO.getLastName();
        this.email = regDTO.getEmail();
        this.setAccountType(enums.AccountType.PATRON);
    }


}
