package com.example.employeeservice.controller;

import com.example.employeeservice.EmployeeTestFactory;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class EmployeeControllerTest {


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

        //given
        Employee employee1 = EmployeeTestFactory.employee1;
        Employee employee2 = EmployeeTestFactory.employee2;
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        when(employeeRepository.findByActive(true)).thenReturn(employeeList);

        //when
        List<Employee> found = employeeController.findAllEmployees(true);

        //then
        assertNotNull(found);
        assertEquals(2,found.size());
    }

    @Test
    public void testFindInactiveEmployees () throws Exception {
        //given
        Employee employee1 = EmployeeTestFactory.inactiveEmployee;
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        when(employeeRepository.findByActive(false)).thenReturn(employeeList);

        //when
        List<Employee> found = employeeRepository.findByActive(false);
        assertNotNull(found);
        assertEquals(1,found.size());

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