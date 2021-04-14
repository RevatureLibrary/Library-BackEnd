package com.library.repo;

import com.library.models.Library;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LibraryRepository extends CrudRepository<Library, Integer> {
}
