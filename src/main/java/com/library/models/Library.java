package com.library.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Library {
    @Id @GeneratedValue
    private int library_id;

    private Timestamp opening_time;
    private Timestamp closing_time;
    private boolean is_open;
    private int capacity;

    @OneToMany
    private Set<Department> departments;
    @OneToMany
    private Set<User> current_librarians;
    @OneToMany
    private Set<User> current_patrons;
}
