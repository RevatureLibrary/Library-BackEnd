package com.library.services;

import com.library.config.LibraryConfig;
import com.library.dto.OpeningAndClosingTimeDTO;
import com.library.models.Library;
import com.library.repo.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    LibraryRepository libraryRepo;

    public Library getLibary() {
        return libraryRepo.findById(LibraryConfig.libraryName).get();
    }

    public OpeningAndClosingTimeDTO getTime() {
        Library library = libraryRepo.findById(LibraryConfig.libraryName).get();
        return new OpeningAndClosingTimeDTO(library.getOpeningTime(), library.getClosingTime());
    }




}
