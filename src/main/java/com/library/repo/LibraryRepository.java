package com.library.repo;

import com.library.models.Library;
import com.library.models.User;
import com.library.models.enums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {

}
