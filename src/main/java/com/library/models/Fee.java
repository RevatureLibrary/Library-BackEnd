package com.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fees")
public class Fee {

    @Id
    @GeneratedValue
    private int id;

    private Timestamp assessed = new Timestamp(System.currentTimeMillis());
    private Timestamp resolved;
    private double amount;

    @Enumerated
    private enums.FeeType feeType;

    @Enumerated
    private enums.FeeStatus feeStatus;

    @ManyToOne
    private User user;

    //@ManyToOne
 //   private Payment payment;
}
