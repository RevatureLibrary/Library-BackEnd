package com.library.controller;

import com.library.models.Book;
import com.library.models.request.BookDTO;
import com.library.services.BookService;
import com.library.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.library.util.AuthorityUtil.*;


@RestController
@RequestMapping(value ={"**/books","/library/books"},consumes = {"application/json","application/json;charset=UTF-8" }, produces = "application/json")

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
    @GetMapping(path = "/department={name}/page={page}")
    public ResponseEntity<?> getAllBooksByDepartment(@PathVariable String name, String page){
        if(isEmployee())
            return ResponseEntity.ok(bookService.getAllByDepartment(name,page));
        else
            return ResponseEntity.status(403).build();

    }


    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO) {
        if (bookDTO == null || bookDTO.getTitle() ==null)
            return ResponseEntity.unprocessableEntity().build();

        if(isEmployee()) {

            Book result = bookService.addBook(bookDTO);
            return ResponseEntity.status(201).body(result);
        }
        return ResponseEntity.status(403).build();
    }
}
