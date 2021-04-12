package com.library.controler;

import com.library.models.Book;
import com.library.services.BookService;
import com.library.services.DepartmentService;
import com.library.util.AuthorityUtil.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.library.util.AuthorityUtil.*;


@RestController
@RequestMapping(value = "*/books",consumes = {"application/json","application/json;charset=UTF-8" }, produces = "application/json")
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getAllBooks(){
        if(isEmployee())
            return ResponseEntity.ok(bookService.getAll());
        else
            return ResponseEntity.status(403).build();

    }


    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        if (book == null || book.getTitle() ==null)
            return ResponseEntity.unprocessableEntity().build();

        if(isEmployee()) {
            bookService.addBook(book);
            System.out.println(book);
            return ResponseEntity.status(201).body(book);
        }
        return ResponseEntity.status(403).build();
    }
}
