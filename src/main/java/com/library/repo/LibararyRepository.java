package com.library.repo;

import com.library.models.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibararyRepository extends JpaRepository<Library, Integer> {

}
