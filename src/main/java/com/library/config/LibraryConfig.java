package com.library.config;

import com.library.models.Department;
import com.library.models.Library;
import com.library.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class LibraryConfig {
    static public int libraryId = 1;

    @Autowired

    @PostConstruct
    public void seedLibrary() {
        if (!libraryRepo.existsById(libraryId)) {
            String libraryName = "William Memorial Library";
            Time openingTime = Time.valueOf("00:00:00");
            Time closingTime = Time.valueOf("23:59:59");
            boolean isOpen = true;
            int capacity = 10;
            Set<Department> departments = new HashSet();
            Set<User> currentLibrarians = new HashSet();
            Set<User> currentPatrons = new HashSet();

            Library library = new Library(libraryId, libraryName, openingTime, closingTime, isOpen, capacity, departments, currentLibrarians, currentPatrons);
            libraryRepo.save(library);
        }


    }
}
