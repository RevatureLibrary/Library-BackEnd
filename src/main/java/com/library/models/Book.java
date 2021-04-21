package com.library.models;

import com.fasterxml.jackson.annotation.*;
import com.library.models.dto.BookDTO;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = "departments")
@EqualsAndHashCode(exclude = "departments")
@AllArgsConstructor
@NoArgsConstructor
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

        @ManyToMany(mappedBy = "books", cascade = CascadeType.REFRESH) //mappedBy tells Hibernate this is the child side of the bidirectional mapping
        @JsonIdentityReference(alwaysAsId = true)
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
        @JsonIgnore
        Set<Department> departments = new HashSet<>();


        public Book(BookDTO bookDTO){
                this.isbn = bookDTO.getIsbn();
                this.title = bookDTO.getTitle();
                this.author = bookDTO.getAuthor();
                this.publisher = bookDTO.getPublisher();
                this.condition = bookDTO.getCondition();
                this.bookStatus = bookDTO.getBookStatus();
                this.departments = new HashSet<>();

        }

}