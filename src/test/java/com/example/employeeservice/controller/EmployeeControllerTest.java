package com.example.employeeservice.controller;

import com.example.employeeservice.EmployeeTestFactory;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.Function;
import com.example.employeeservice.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeeControllerTest {
    private final static SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    private EmployeeController employeeController;
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeController = new EmployeeController(employeeRepository);
    }

    @AfterEach
    void tearDown() {
    }



    @Test
    public void testFindActiveEmployees () throws Exception {

    }

    @Test
    public void testFindEmployeeByEmployeeId() throws Exception {
        //given
        Employee employee = EmployeeTestFactory.employee1;

        when(employeeRepository.findByEmployeeID(employee.getEmployeeID())).thenReturn(employee);

        //when
        Employee found = employeeController.findByEmployeeID(employee.getEmployeeID());

        //then
        assertNotNull(found);
        assertEquals("Jan", found.getFirstName());
    }

}