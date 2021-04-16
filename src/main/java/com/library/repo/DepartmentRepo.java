package com.library.repo;

import com.library.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {


    Department findByNameEqualsIgnoreCase(String name);

}
