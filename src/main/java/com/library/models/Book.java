package com.library.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int isbn;
    String title;
    String author;
    String publisher;
    @Enumerated
    enums.Condition condition;
    @Enumerated
    enums.BookStatus bookStatus;
    @ManyToMany(mappedBy = "books") //mappedBy tells Hibernate this is the child side of the bidirectional mapping
    Set<Department> departments;

    public Book() {
    }

    public Book(int id, int isbn, String title, String author, String publisher, enums.Condition condition, enums.BookStatus bookStatus, HashSet<Department> departments) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.condition = condition;
        this.bookStatus = bookStatus;
        this.departments = departments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public enums.Condition getCondition() {
        return condition;
    }

    public void setCondition(enums.Condition condition) {
        this.condition = condition;
    }

    public enums.BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(enums.BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
}
