package com.example.employeeservice.repository;

import com.example.employeeservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByActive(Boolean active);

    Employee findByEmployeeID(String employeeID);

}
