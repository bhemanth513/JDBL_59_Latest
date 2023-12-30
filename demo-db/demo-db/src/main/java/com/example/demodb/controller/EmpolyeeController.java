package com.example.demodb.controller;

import com.example.demodb.dto.CreateEmployeeRequest;
import com.example.demodb.dto.UpdateEmployeeRequest;
import com.example.demodb.model.Employee;
import com.example.demodb.service.EmployeeService;
import com.example.demodb.dto.CreateEmployeeRequest;
import com.example.demodb.dto.UpdateEmployeeRequest;
import com.example.demodb.model.Employee;
import com.example.demodb.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class EmpolyeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody @Valid CreateEmployeeRequest request) throws SQLException {
        return employeeService.create(request);
    }

    @GetMapping("/employee/{empId}")
    public Employee getEmployee(@PathVariable("empId") String id){
        return employeeService.get(id);
    }

    @GetMapping("/empoyee/all")
    public List<Employee> getEmployee() throws SQLException {
        return employeeService.get();
    }

    @PutMapping("/employee/{empId}")
    public Employee updateEmployee(@PathVariable("empId") String id, @RequestBody @Valid UpdateEmployeeRequest request){
        return employeeService.update(id,request);
    }

    @DeleteMapping("employee/{empId}")
    public Employee deleteEmployee(@RequestParam("empId") String id){
        return employeeService.delete(id);
    }
}
