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
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.text.SimpleDateFormat;

@RestController
public class EmployeeController {
    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * GET /werknemers?active=true
     */
    @GetMapping("/werknemers")
    public List<Employee> findAllEmployees(@RequestParam(value = "active", required = false) Boolean active) {
        if (active == null) {
            return employeeRepository.findAll();
        } else {
            return employeeRepository.findByActief(active);
        }
    }

    /**
     * GET /werknemers/rijksregisternummer/91827363
     */
    @GetMapping("/werknemers/rijksregisternummer/{rijksregisternummer}")
    public Employee findByRijksRegisterNummer(@PathVariable("rijksregisternummer") String rijksregisternummer) {
        return employeeRepository.findByRijksregisternummer(rijksregisternummer);
    }


    @PostConstruct
    public void addEmployeesOnStartup() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

        Employee employee1 = new Employee(
                Arrays.asList(
                        new Werkuren(
                                formatter.parse("31-Dec-1998 06:37:50"),
                                formatter.parse("31-Dec-1998 17:37:50")
                        ),
                        new Werkuren(
                                formatter.parse("01-Jan-1999 06:37:50"),
                                formatter.parse("01-Jan-1999 17:37:50")
                        )),
                new Functie("BAAS"),
                "Jan",
                "Vermeulen",
                "0495898989",
                formatter.parse("31-Dec-1998 06:37:10"),
                null,
                true,
                "1238912039"
        );

        Employee employee2 = new Employee(
                Arrays.asList(
                        new Werkuren(
                                formatter.parse("30-Dec-1998 06:37:50"),
                                formatter.parse("30-Dec-1998 17:37:50")
                        ),
                        new Werkuren(
                                formatter.parse("10-Jan-1999 06:37:50"),
                                formatter.parse("10-Jan-1999 17:37:50")
                        )),
                new Functie("CEO2.0"),
                "Wout",
                "Vermeulen",
                "021312321",
                formatter.parse("31-Dec-1998 06:37:10"),
                null,
                true,
                "21421421412"
        );
        saveIfNotExists(employee1);
        saveIfNotExists(employee2);
    }

    public void saveIfNotExists(Employee employee){
        if (employeeRepository.findByRijksregisternummer(employee.getRijksregisternummer()) == null) {
            logger.info("Saving new employee: {}", employee.getRijksregisternummer());
            employeeRepository.save(employee);
        } else {
            logger.info("Ignoring save request, an employee with rijksnr: {} already exists", employee.getRijksregisternummer());
        }
    }
}