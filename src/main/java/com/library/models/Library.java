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

    @Id @Column(name = "library_name")
    String libraryName;
    @Column(name = "opening_time")
    private Time openingTime;
    @Column(name = "closing_time")
    private Time closingTime;
    @Column(name = "is_open")
    private boolean isOpen;
    private int capacity;

    @OneToMany
    private Set<Department> departments;
    @OneToMany
    private Set<User> currentLibrarians;
    @OneToMany
    private Set<User> currentPatrons;
}
