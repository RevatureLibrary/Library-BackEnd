package com.library.services;

import com.library.models.Book;
import com.library.models.Department;
import com.library.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookService {
    @Autowired
    BookRepo bookRepo;

    @Autowired
    DepartmentService departmentService;

    public List<Book> getAll() {
       return bookRepo.findAll();
    }

    @Transactional
    public void addBook(Book book) {
        if(book.getDepartments()==null)
            book.setDepartments(new HashSet<>());
        if(book.getDepartments().isEmpty())
            departmentService.addToUnsorted(book);


        System.out.println(book);
        bookRepo.save(book);
    }
}

