package com.library.config;

import com.library.models.Department;
import com.library.models.Library;
import com.library.models.User;
import com.library.repo.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

public class LibraryConfig {
    static public String libraryName = "William Memorial Library";
    @Autowired
    private LibraryRepository libraryRepo;

    static public void seedLibrary() {

        Time openingTime = Time.valueOf("00:00:00");
        Time closingTime = Time.valueOf("23:59:59");
        boolean isOpen = true;
        int capacity = 10;
        Set<Department> departments = new HashSet();
        Set<User> currentLibrarians = new HashSet();
        Set<User> currentPatrons = new HashSet();

        Library library = new Library(libraryName, openingTime, closingTime, isOpen, capacity, departments, currentLibrarians, currentPatrons);


    }
}
