package com.library.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @ManyToOne
    //@JsonIgnoreProperties(value = "payments")
    User user;

    Double amount;
    Timestamp time;
    @OneToMany(mappedBy = "payment",targetEntity = Fee.class)
    Set<Fee> feesPaid;
}
