package com.library.services;

import com.library.models.Book;
import com.library.models.Department;
import com.library.models.dto.BookDTO;
import com.library.models.enums;
import com.library.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        if (dep.isEmpty())
            departmentService.addToUnsorted(result);
        else
            departmentService.addToDepartments(result);

        return result;
    }

    public Page<Book> getAllByDepartment(String name, Integer page, Integer size) {
        Pageable of = PageRequest.of(page, size);
        return bookRepo.findAllByDepartments_NameContainsIgnoreCase(name, of);
    }

    public Book getByBookId(Integer id) {
        return bookRepo.getById(id);
    }

    public enums.Condition cycleCondition(enums.Condition condition) {
        switch (condition) {
            case WILLIAM:
                return enums.Condition.GOOD;

            case GOOD:
                return enums.Condition.FAIR;

            case FAIR:
                return enums.Condition.POOR;

            case POOR:
                return enums.Condition.TRASHED;

            default:
                return enums.Condition.WILLIAM;
        }
    }
}
