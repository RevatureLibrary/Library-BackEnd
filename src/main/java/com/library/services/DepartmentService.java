package com.library.services;

import com.library.models.Book;
import com.library.models.Department;
import com.library.repo.BookRepo;
import com.library.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import sun.reflect.generics.tree.Tree;

import java.util.*;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo departmentRepo;
    @Autowired
    BookRepo bookRepo;
    
    public void save(Department d) {
        departmentRepo.save(d);
    }

    public Set<Book> getOrCreateBooks(Department d) {
        return ((d.getBooks()==null)? new HashSet<>():d.getBooks());
    }

    public void addToUnsorted(Book book) {
        Department us = unsorted();
        System.out.println(us);
        us.setBooks(getOrCreateBooks(us));
        bookRepo.save(book);
        us.getBooks().add(book);
        System.out.println(us);
        departmentRepo.save(us);
    }

    private Department unsorted() {
       return findOne("unsorted");
    }

    public List<Department> getAll() {
        return departmentRepo.findAll();
    }

    public void addToDepartments(Book book) {
        Book b = bookRepo.save(book);
        for (Department d :
                b.getDepartments()) {
            d.setBooks(getOrCreateBooks(d));
            d.getBooks().add(b);

            departmentRepo.save(d);
        }
    }

    public Department findOne(String unsorted) {
        return departmentRepo.findByNameEqualsIgnoreCase(unsorted);

    }

    public HashSet<Department> parseBookDTO(String[] departments) {
            HashSet<Department> result = new HashSet<>();
            if(departments!=null)
                for (String s :
                        departments) {
                    result.add(departmentRepo.findByNameEqualsIgnoreCase(s));
                }
            return result;
    }
}