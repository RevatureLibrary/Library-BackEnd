package com.library.models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Set;
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@ToString(exclude = "books")
@EqualsAndHashCode(exclude = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    //owner/parent side of mapping defines join table
    @JoinTable(name = "Department_Book",
        joinColumns = {@JoinColumn(name = "department_id")},
        inverseJoinColumns = {@JoinColumn(name = "book_id")})
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private Set<Book> books;




}
