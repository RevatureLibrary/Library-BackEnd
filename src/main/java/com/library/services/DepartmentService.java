package com.library.services;

import com.library.models.Book;
import com.library.models.Department;
import com.library.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo departmentRepo;


    public Department getUnsorted() {
        return departmentRepo.findByNameIgnoreCase("Unsorted");
    }


    @Bean
    public Department UnsortedBean() {
        return new Department(0, "Unsorted", null);
    }

    public void save(Department d) {
        departmentRepo.save(d);
    }

    public Set<Book> getOrCreateBooks(Department d) {
        return ((d.getBooks()==null)? new HashSet<>():d.getBooks());
    }

    public void addToUnsorted(Book book) {
        Department us = getUnsorted();
        System.out.println(us);
        us.setBooks(getOrCreateBooks(us));
        us.getBooks().add(book);
        System.out.println(us);
        departmentRepo.save(us);
    }
}