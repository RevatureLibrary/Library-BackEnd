package com.library.repo;

import com.library.models.Library;
import org.springframework.data.jpa.repository.JpaRepository;

    Library findByNameEqualsIgnoreCase(String libraryName);
