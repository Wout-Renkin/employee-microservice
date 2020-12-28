package com.example.employeeservice.controller;

import com.example.employeeservice.EmployeeTestFactory;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;



    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonEmployees() throws Exception{

        Employee employee1 = EmployeeTestFactory.employee1;
        Employee employee2 = EmployeeTestFactory.employee2;
        List<Employee> employees = Arrays.asList(employee1, employee2);

        given(employeeRepository.findAll()).willReturn(employees);

        mockMvc.perform(get("/employees"))
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
    public void givenEmployees_whenGetActiveEmployees_thenReturnJsonActiveEmployees() throws Exception{

        Employee employee1 = EmployeeTestFactory.employee1;
        Employee employee2 = EmployeeTestFactory.employee2;
        List<Employee> employees = Arrays.asList(employee1, employee2);

        given(employeeRepository.findByActive(true)).willReturn(employees);

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

        Employee employee1 = EmployeeTestFactory.inactiveEmployee;
        List<Employee> employees = Arrays.asList(employee1);

        given(employeeRepository.findByActive(false)).willReturn(employees);

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

    @Test
    public void givenEmployee_whenGetEmployeeByEmployeeId_thenReturnJsonEmployee() throws Exception{

        Employee employee1 = EmployeeTestFactory.employee1;

        given(employeeRepository.findByEmployeeID(employee1.getEmployeeID())).willReturn(employee1);

        mockMvc.perform(get("/employees/{employeeID}", employee1.getEmployeeID()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.firstName", is("Jan")))
                .andExpect(jsonPath("$.lastName", is("Vermeulen")))
                .andExpect(jsonPath("$.phoneNumber", is("0495898989")))
                .andExpect(jsonPath("$.startContract", is("1998-12-31T06:37:10.000+00:00")))
                .andExpect(jsonPath("$.endContract", is(IsNull.nullValue())))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.workingHours[0].startTimestamp", is("1998-12-31T06:37:50.000+00:00")))
                .andExpect(jsonPath("$.workingHours[0].stopTimestamp", is("1998-12-31T17:37:50.000+00:00")))
                .andExpect(jsonPath("$.workingHours[1].startTimestamp", is("1999-01-01T06:37:50.000+00:00")))
                .andExpect(jsonPath("$.workingHours[1].stopTimestamp", is("1999-01-01T17:37:50.000+00:00")))
                .andExpect(jsonPath("$.function.functionName", is("BAAS")));
    }

}