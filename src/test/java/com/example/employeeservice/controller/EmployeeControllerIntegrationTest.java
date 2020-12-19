package com.example.employeeservice.controller;

import com.example.employeeservice.EmployeeTestFactory;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.Function;
import com.example.employeeservice.model.WorkingHours;
import com.example.employeeservice.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.hamcrest.core.IsNull;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerIntegrationTest {
    private final static SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;
    private Employee employee1;
    private Employee employee2;
    private Employee inactiveEmployee;



    @BeforeEach
    void setUp() throws ParseException {
        employeeRepository.deleteAll();
        this.employee1 = EmployeeTestFactory.employee1;
        this.employee2 = EmployeeTestFactory.employee2;
        this.inactiveEmployee = EmployeeTestFactory.inactiveEmployee;
        employeeRepository.save(this.employee1);
        employeeRepository.save(this.employee2);
        employeeRepository.save(this.inactiveEmployee);
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    public void givenEmployee_whenGetEmployeeByEmployeeId_thenReturnJsonEmployee() throws Exception{
        mockMvc.perform(get("/employees/{employeeID}", employee1.getEmployeeID()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonEmployees() throws Exception{
        mockMvc.perform(get("/employees"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].firstName", is("Jan")))
                .andExpect(jsonPath("$[0].lastName", is("Vermeulen")))
                .andExpect(jsonPath("$[0].phoneNumber", is("0495898989")))
                .andExpect(jsonPath("$[0].startContract", is("1998-12-31T06:37:10.000+00:00")))
                .andExpect(jsonPath("$[0].endContract", is(IsNull.nullValue())))
                .andExpect(jsonPath("$[0].active", is(true)))
                .andExpect(jsonPath("$[0].workingHours[0].startTimestamp", is("1998-12-31T06:37:50.000+00:00")))
                .andExpect(jsonPath("$[0].workingHours[0].stopTimestamp", is("1998-12-31T17:37:50.000+00:00")))
                .andExpect(jsonPath("$[0].workingHours[1].startTimestamp", is("1999-01-01T06:37:50.000+00:00")))
                .andExpect(jsonPath("$[0].workingHours[1].stopTimestamp", is("1999-01-01T17:37:50.000+00:00")))
                .andExpect(jsonPath("$[0].function.functionName", is("BAAS")))
                .andExpect(jsonPath("$[1].firstName", is("Wout")))
                .andExpect(jsonPath("$[1].lastName", is("Renkin")))
                .andExpect(jsonPath("$[1].phoneNumber", is("05770523")))
                .andExpect(jsonPath("$[1].startContract", is("1998-12-31T06:37:10.000+00:00")))
                .andExpect(jsonPath("$[1].endContract", is(IsNull.nullValue())))
                .andExpect(jsonPath("$[1].active", is(true)))
                .andExpect(jsonPath("$[1].workingHours[0].startTimestamp", is("1998-12-31T06:37:50.000+00:00")))
                .andExpect(jsonPath("$[1].workingHours[0].stopTimestamp", is("1998-12-31T17:37:50.000+00:00")))
                .andExpect(jsonPath("$[1].workingHours[1].startTimestamp", is("1999-01-01T06:37:50.000+00:00")))
                .andExpect(jsonPath("$[1].workingHours[1].stopTimestamp", is("1999-01-01T17:37:50.000+00:00")))
                .andExpect(jsonPath("$[1].function.functionName", is("CEO")))
                .andExpect(jsonPath("$[2].firstName", is("Senne")))
                .andExpect(jsonPath("$[2].lastName", is("Castermans")))
                .andExpect(jsonPath("$[2].phoneNumber", is("047705803")))
                .andExpect(jsonPath("$[2].startContract", is("1998-12-31T06:37:10.000+00:00")))
                .andExpect(jsonPath("$[2].endContract", is(IsNull.nullValue())))
                .andExpect(jsonPath("$[2].active", is(false)))
                .andExpect(jsonPath("$[2].workingHours[0].startTimestamp", is("1998-12-31T06:37:50.000+00:00")))
                .andExpect(jsonPath("$[2].workingHours[0].stopTimestamp", is("1998-12-31T17:37:50.000+00:00")))
                .andExpect(jsonPath("$[2].workingHours[1].startTimestamp", is("1999-01-01T06:37:50.000+00:00")))
                .andExpect(jsonPath("$[2].workingHours[1].stopTimestamp", is("1999-01-01T17:37:50.000+00:00")))
                .andExpect(jsonPath("$[2].function.functionName", is("Mechanic")));


    }

    @Test
    public void givenEmployees_whenGetActiveEmployees_thenReturnJsonActiveEmployees() throws Exception{
        mockMvc.perform(get("/employees?active=true"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("Jan")))
                .andExpect(jsonPath("$[0].lastName", is("Vermeulen")))
                .andExpect(jsonPath("$[0].phoneNumber", is("0495898989")))
                .andExpect(jsonPath("$[0].startContract", is("1998-12-31T06:37:10.000+00:00")))
                .andExpect(jsonPath("$[0].endContract", is(IsNull.nullValue())))
                .andExpect(jsonPath("$[0].active", is(true)))
                .andExpect(jsonPath("$[0].workingHours[0].startTimestamp", is("1998-12-31T06:37:50.000+00:00")))
                .andExpect(jsonPath("$[0].workingHours[0].stopTimestamp", is("1998-12-31T17:37:50.000+00:00")))
                .andExpect(jsonPath("$[0].workingHours[1].startTimestamp", is("1999-01-01T06:37:50.000+00:00")))
                .andExpect(jsonPath("$[0].workingHours[1].stopTimestamp", is("1999-01-01T17:37:50.000+00:00")))
                .andExpect(jsonPath("$[0].function.functionName", is("BAAS")))
                .andExpect(jsonPath("$[1].firstName", is("Wout")))
                .andExpect(jsonPath("$[1].lastName", is("Renkin")))
                .andExpect(jsonPath("$[1].phoneNumber", is("05770523")))
                .andExpect(jsonPath("$[1].startContract", is("1998-12-31T06:37:10.000+00:00")))
                .andExpect(jsonPath("$[1].endContract", is(IsNull.nullValue())))
                .andExpect(jsonPath("$[1].active", is(true)))
                .andExpect(jsonPath("$[1].workingHours[0].startTimestamp", is("1998-12-31T06:37:50.000+00:00")))
                .andExpect(jsonPath("$[1].workingHours[0].stopTimestamp", is("1998-12-31T17:37:50.000+00:00")))
                .andExpect(jsonPath("$[1].workingHours[1].startTimestamp", is("1999-01-01T06:37:50.000+00:00")))
                .andExpect(jsonPath("$[1].workingHours[1].stopTimestamp", is("1999-01-01T17:37:50.000+00:00")))
                .andExpect(jsonPath("$[1].function.functionName", is("CEO")));
    }

    @Test
    public void givenEmployees_whenGetInactiveEmployees_thenReturnJsonInactiveEmployees() throws Exception{
        mockMvc.perform(get("/employees?active=false"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is("Senne")))
                .andExpect(jsonPath("$[0].lastName", is("Castermans")))
                .andExpect(jsonPath("$[0].phoneNumber", is("047705803")))
                .andExpect(jsonPath("$[0].startContract", is("1998-12-31T06:37:10.000+00:00")))
                .andExpect(jsonPath("$[0].endContract", is(IsNull.nullValue())))
                .andExpect(jsonPath("$[0].active", is(false)))
                .andExpect(jsonPath("$[0].workingHours[0].startTimestamp", is("1998-12-31T06:37:50.000+00:00")))
                .andExpect(jsonPath("$[0].workingHours[0].stopTimestamp", is("1998-12-31T17:37:50.000+00:00")))
                .andExpect(jsonPath("$[0].workingHours[1].startTimestamp", is("1999-01-01T06:37:50.000+00:00")))
                .andExpect(jsonPath("$[0].workingHours[1].stopTimestamp", is("1999-01-01T17:37:50.000+00:00")))
                .andExpect(jsonPath("$[0].function.functionName", is("Mechanic")));

    }

}