package com.library.controller;

import com.library.models.Book;
import com.library.models.dto.BookDTO;
import com.library.services.BookService;
import com.library.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

import static com.library.util.AuthorityUtil.*;


@RestController
@Transactional
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
    @GetMapping(path = "/{name}/{page}")
    public ResponseEntity<?> getAllBooksByDepartment(@PathVariable String name, @PathVariable Integer page, @RequestParam(required = false,defaultValue = "3",name = "size") Integer size){
        System.out.println(name + page + size);
        Page<Book> results = bookService.getAllByDepartment(name,page,size);
        if(results != null)
            return ResponseEntity.ok(results);

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
