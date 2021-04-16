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
@JsonIdentityReference(alwaysAsId = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
@Table(name = "departments")
public class Department implements Comparable<Department> {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    @ManyToMany(fetch = FetchType.EAGER,cascade ={ CascadeType.ALL, CascadeType.MERGE})
    //owner/parent side of mapping defines join table
    @JoinTable(name = "Department_Book",
        joinColumns = {@JoinColumn(name = "department_id")},
        inverseJoinColumns = {@JoinColumn(name = "book_id")})
    @JsonIgnore
    private Set<Book> books;

    @Override
    public int compareTo(Department d) {
        return this.name.compareTo(d.getName());
    }
}
