package com.library.repo;

import com.library.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepo extends JpaRepository<Book, Integer> {


    Page<Book> findAllByDepartments_NameContains(String name, PageRequest of);
}
