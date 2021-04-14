package com.library.controler;

import com.library.models.Department;
import com.library.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.TreeSet;

@RestController
@RequestMapping(value = {"library/departments","**/departments"},consumes = "application/json", produces = "application/json")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getAllDepartments(){
        List<Department> result = departmentService.getAll();
        if(result == null ||result.isEmpty())
            return ResponseEntity.status(403).build();
        else
            return ResponseEntity.ok(result);

    }



}
