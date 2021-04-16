package com.library.services;

import com.library.controler.DepartmentController;
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
        book.getDepartments().add(us);
        us.setBooks(getOrCreateBooks(us));
        bookRepo.save(book);
        us.getBooks().add(book);
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
        b.getDepartments().forEach((Department d)-> {
            d.setBooks(getOrCreateBooks(d));
            d.getBooks().add(b);

            departmentRepo.save(d);
        });
    }

    public Department findOne(String unsorted) {
        return departmentRepo.findByNameEqualsIgnoreCase(unsorted);

    }

    public HashSet<Department> parseBookDTODepartments(String[] departments) {
            HashSet<Department> result = new HashSet<>();
            if(departments!=null)
                for (String s :
                        departments) {
                    result.add(departmentRepo.findByNameEqualsIgnoreCase(s));
                }
            return result;
    }

    public boolean departmentExists(String name) {
        return (departmentRepo.findByNameEqualsIgnoreCase(name) != null);
    }

    public void createDepartment(Department newDept) {
        departmentRepo.save(newDept);
    }

    public void deleteDepartment(String name) {
        Department department = departmentRepo.findByNameEqualsIgnoreCase(name);
        departmentRepo.delete(department);
    }

    public Set<Book> getBooksByName(String name) {
        Department department = departmentRepo.findByNameEqualsIgnoreCase(name);
        return department.getBooks();
    }

    public void updateName(DepartmentController.UpdateForm updateForm) {
        Department department = departmentRepo.findByNameEqualsIgnoreCase(updateForm.getFrom());
        department.setName(updateForm.getTo());
        departmentRepo.save(department);
    }

}