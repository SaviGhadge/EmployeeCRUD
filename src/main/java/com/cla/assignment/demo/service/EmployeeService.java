package com.cla.assignment.demo.service;

import com.cla.assignment.demo.entity.Employee;
import com.cla.assignment.demo.exception.NotFoundException;
import com.cla.assignment.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository = null;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee create(Employee employee) {
        try{
            Employee newEmployee = employeeRepository.save(employee);
            return newEmployee;
        } catch(Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<Employee> getAll() {
        try{
            List<Employee> employeeList = employeeRepository.findAll();
            return employeeList;
        } catch (Exception ex) {
            throw new NotFoundException("Failed to fetch");
        }
    }

    public Employee getEmployeeDetails(Integer empId){
        Employee emp = employeeRepository.findById(empId)
                .orElseThrow(() -> new NotFoundException());
        return emp;
    }

    public Employee getEmployeeByEmail(String emailId) {
            Employee emp = employeeRepository.findByEmailId(emailId);
            if(emp == null) {
                throw new NotFoundException("Employee does not exist");
            }
            return emp;

    }

    public Employee updateEmployeeDetails(Integer empId, Employee update) {
        Employee emp = employeeRepository.findById(empId)
                    .orElseThrow(() -> new NotFoundException("Employee does not exist"));

        emp.setDepartment(update.getDepartment());
        emp.setDesignation(update.getDesignation());
        employeeRepository.save(emp);

        return emp;
    }

    public Employee deleteEmployee(Integer empId) {
        Employee emp = employeeRepository.findById(empId)
                .orElseThrow(() -> new NotFoundException("Employee does not exist"));

        employeeRepository.delete(emp);
        return emp;
    }

}
