package com.cla.assignment.demo.entity;

import com.cla.assignment.demo.exception.ArgumentNotValidException;
import com.cla.assignment.demo.model.Department;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Table(name = "Employee", uniqueConstraints={@UniqueConstraint(columnNames={"emailId"})})
public class Employee {

    @Id
    @NotNull
    @Column(name = "Id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "FirstName", updatable = false)
    @Pattern(regexp = "^[A-Za-z]{2,20}$",
    message = "First name must be alphabets only")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]{2,20}$",
    message = "Last name must be alphabets only")
    @Column(name = "LastName", updatable = false)
    private String lastName;

    @NotBlank
    @Column(name = "EmailId", unique = true, updatable = false)
    private String emailId;

    @NotBlank
    @Column(name = "DateOfBirth", updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")

    private LocalDate dob;

    @NotBlank
    @Column(name = "Designation")
    private String designation;

    @NotBlank
    @Column(name = "Department")
    private Department department;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, String emailId,
                    LocalDate dob, String designation, Department department) throws ArgumentNotValidException {
        calculateAge(); //throw validation exception for Invalid age
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.dob = dob;
        this.designation = designation;
        this.department = department;
    }

    public Employee(String designation, Department department) {
        this.designation = designation;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getDesignation() {
        return designation;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    //TODO: Need to work on. Not working
    private int calculateAge() throws ArgumentNotValidException {
        LocalDate now = LocalDate.now();
        int age = Period.between(dob, now).getYears();
        System.out.println("Age Calculated: " + age);
        if(age < 21 || age > 60) {
            throw new ArgumentNotValidException("Age must be between 21 to 60");
        }
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return getId() == employee.getId() && getEmailId().equals(employee.getEmailId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmailId());
    }
}
