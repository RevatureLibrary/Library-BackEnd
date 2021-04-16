package com.library.controller;

import com.library.models.Department;
import com.library.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.library.util.AuthorityUtil.isEmployee;

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

    @PostMapping
    public ResponseEntity<?> createNewDepartment(@RequestBody String name){
        if(!isEmployee())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        if(departmentService.departmentExists(name))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Department " +name+ " already exists.");

        Department newDept = new Department(0,name,null);
        departmentService.createDepartment(newDept);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDept);
    }


}
