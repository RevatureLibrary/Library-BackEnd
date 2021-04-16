package com.library.services;

import com.library.models.Book;
import com.library.models.Department;
import com.library.models.request.BookDTO;
import com.library.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

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
    public Book addBook(BookDTO book) {
        TreeSet<Department> dep = departmentService.parseBookDTODepartments(book.getDepartments());
        Book result = new Book(book);
        result.setDepartments(dep);
        if(dep.isEmpty())
            departmentService.addToUnsorted(result);
        else
            departmentService.addToDepartments(result);

        return result;
    }

    public Page<Book> getAllByDepartment(String page, String name) {
        return bookRepo.findAllByDepartments_NameContains(name, PageRequest.of(Integer.parseInt(page),20));
    }




}

