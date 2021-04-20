package com.library.models.request;

import com.library.models.enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Enumerated;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {

    int id;
    int isbn;
    String title;
    String author;
    String publisher;
    enums.Condition condition;
    enums.BookStatus bookStatus;
    String[] departments;

}
