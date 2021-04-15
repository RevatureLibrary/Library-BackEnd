package com.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Library {
    @Id
    int id;
    @Column(name = "library_name")
    String name;
    @Column(name = "opening_time")
    private Time openingTime;
    @Column(name = "closing_time")
    private Time closingTime;
    @Transient
    private boolean isOpen;
}
