package com.library.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany
    //owner/parent side of mapping defines join table
    @JoinTable(name = "Department_Book",
        joinColumns = {@JoinColumn(name = "department_id")},
        inverseJoinColumns = {@JoinColumn(name = "book_id")})
    private Set<Book> books;

    public Department() {
    }

    public Department(int id, String name, Set<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
