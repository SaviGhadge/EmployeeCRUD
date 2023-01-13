package com.cla.assignment.demo.controller;

import com.cla.assignment.demo.entity.Employee;
import com.cla.assignment.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee newEmp = employeeService.create(employee);
        return new ResponseEntity<>(newEmp, HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> fetchAll() {
        List<Employee> employeeList = employeeService.getAll();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<Employee> fetchEmployeeDetails(@PathVariable(value = "empId") Integer empId) {
        Employee employee = employeeService.getEmployeeDetails(empId);
        return new ResponseEntity<>(employee, HttpStatus.FOUND);
    }

    @GetMapping("/employee")
    public ResponseEntity<Employee> findByEmail(@RequestHeader(value = "email-id") String emailId) {
        Employee employee = employeeService.getEmployeeByEmail((emailId));
        return new ResponseEntity<>(employee, HttpStatus.FOUND);
    }

    @PutMapping("/employee/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "empId") Integer empId, @RequestBody Employee update) {
        Employee employee = employeeService.updateEmployeeDetails(empId, update);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{empId}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "empId") Integer empId) {
        Employee employee = employeeService.deleteEmployee(empId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
