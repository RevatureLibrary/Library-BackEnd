package com.library.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.library.models.request.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

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

    public User(String username, String password, String firstName, String lastName, @Email String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
