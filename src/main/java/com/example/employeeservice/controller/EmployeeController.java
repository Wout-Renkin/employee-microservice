package com.example.employeeservice.controller;

import com.example.employeeservice.model.*;
import com.example.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@RestController
public class EmployeeController {
    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private EmployeeRepository employeeRepository;

//    @Autowired
//    private EmployeeRepository employeeRepository;

    public EmployeeController (@Autowired EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * GET /employees?active=true
     */
    @GetMapping("/employees")
    public List<Employee> findAllEmployees(@RequestParam(value = "active", required = false) Boolean active) {
        if (active == null) {
            return employeeRepository.findAll();
        } else {
            return employeeRepository.findByActive(active);
        }
    }

    @GetMapping("/employees/{employeeID}")
    public Employee findByEmployeeID(@PathVariable String employeeID) {
        return employeeRepository.findByEmployeeID(employeeID);
    }

    @PostConstruct
    public void addEmployeesOnStartup() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        Employee employee1 = new Employee(
                Arrays.asList(
                        new WorkingHours(
                                formatter.parse("31-Dec-1998 06:37:50"),
                                formatter.parse("31-Dec-1998 17:37:50")
                        ),
                        new WorkingHours(
                                formatter.parse("01-Jan-1999 06:37:50"),
                                formatter.parse("01-Jan-1999 17:37:50")
                        )),
                new Function("BAAS"),
                "Jan",
                "Vermeulen",
                "0495898989",
                formatter.parse("31-Dec-1998 06:37:10"),
                null,
                true

        );

        Employee employee2 = new Employee(
                Arrays.asList(
                        new WorkingHours(
                                formatter.parse("30-Dec-1998 06:37:50"),
                                formatter.parse("30-Dec-1998 17:37:50")
                        ),
                        new WorkingHours(
                                formatter.parse("10-Jan-1999 06:37:50"),
                                formatter.parse("10-Jan-1999 17:37:50")
                        )),
                new Function("CEO2.0"),
                "Wout",
                "Vermeulen",
                "021312321",
                formatter.parse("31-Dec-1998 06:37:10"),
                null,
                true

        );

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
    }



}