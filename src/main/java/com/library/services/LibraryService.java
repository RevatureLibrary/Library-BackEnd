package com.library.services;

import com.library.dto.OpeningAndClosingTimeDTO;
import com.library.models.Department;
import com.library.models.Library;
import com.library.repo.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LibraryService {

    @Autowired
    LibraryRepository libraryRepo;

    public Library getLibraryByName(String name) {
        return libraryRepo.findByNameEqualsIgnoreCase(name);
    }

//    public OpeningAndClosingTimeDTO getTime(Library library) {
//        Library library = libraryRepo.findById(LibraryConfig.libraryId).get();
//        return new OpeningAndClosingTimeDTO(library.getOpeningTime(), library.getClosingTime());
//    }


    public boolean existsByName(String libraryName) {
        return (libraryRepo.findByNameEqualsIgnoreCase(libraryName)==null);
    }

    public void create(Library library) {
        libraryRepo.save(library);
    }
    public void addDepartment(Department department) {
        Library library = libraryRepo.findById(LibraryConfig.libraryId).get();
        library.getDepartments().add(department);
        libraryRepo.save(library);
    }

    public void updateLibraryName(String libraryName) {
        Library library = libraryRepo.findById(LibraryConfig.libraryId).get();
        library.setLibraryName(libraryName);
        libraryRepo.save(library);
    }

    public LibraryInfo getLibraryInfo() {
        Library library = libraryRepo.findById(LibraryConfig.libraryId).get();
        return new LibraryInfo(library.getLibraryName(), library.getOpeningTime(), library.getClosingTime(), library.isOpen());
    }

}
