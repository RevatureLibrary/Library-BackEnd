package com.library.repo;

import com.library.models.Book;
import com.library.models.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepo extends JpaRepository<Book, Integer> {
    Book getById(Integer id);

    Page<Book> findAllByDepartments_NameContainsIgnoreCase(String name, Pageable of);
}
