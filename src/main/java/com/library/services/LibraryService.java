package com.library.services;

import com.library.dto.LibraryInfo;
import com.library.models.Library;
import com.library.repo.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LibraryService {

    @Autowired
    LibraryRepository libraryRepo;

//    public Library getLibraryByName(String name) {
//        return libraryRepo.findByNameEqualsIgnoreCase(name);
//    }
//
//    public boolean existsByName(String libraryName) {
//        return (libraryRepo.findByNameEqualsIgnoreCase(libraryName)==null);
//    }
//
//    public LibraryInfo getLibraryInfo() {
//        Library library = libraryRepo.findById(1).get();
//        return new LibraryInfo(library.getLibraryName(), library.getOpeningTime(), library.getClosingTime(), library.isOpen());
//    }

    public Library getLibrary() {
        Optional<Library> library = libraryRepo.findById(1);
        return library.orElse(null);
    }

    public void update(Library library) {
        libraryRepo.save(library);
    }
}
