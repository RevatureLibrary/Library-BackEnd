package com.library.models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
        @org.springframework.data.annotation.AccessType(org.springframework.data.annotation.AccessType.Type.PROPERTY)
        int id;
        int isbn;
        String title;
        String author;
        String publisher;
        @Enumerated
        enums.Condition condition;
        @Enumerated
        enums.BookStatus bookStatus;
        @JsonIgnore
        @ManyToMany(mappedBy = "books") //mappedBy tells Hibernate this is the child side of the bidirectional mapping
        @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
        Set<Department> departments = new HashSet<>();
}
