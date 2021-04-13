package com.library.services;

import com.library.config.LibraryConfig;
import com.library.models.Library;
import com.library.repo.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class LibraryService {

    @Autowired
    LibraryRepository libraryRepo;

    public Optional<Library> getLibary() {
        return libraryRepo.findById(LibraryConfig.libraryName);
    }
}
