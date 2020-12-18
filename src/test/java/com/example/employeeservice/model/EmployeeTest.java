package com.example.employeeservice.model;

import com.example.employeeservice.EmployeeTestFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
   private final static SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    @Test
    public void testNewInstance() throws Exception {

        Employee employee = EmployeeTestFactory.employee1;

        Assertions.assertEquals("Vermeulen", employee.getLastName());
    }

    @Test
    @DisplayName("A start contract date should always be before the end contract date")
    public void testNewInstanceWithBadEndContract() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Employee employee = new Employee(
                    Arrays.asList(),
                    new Function("BAAS"),
                    "Jan",
                    "Vermeulen",
                    "0495898989",
                    formatter.parse("31-Dec-1998 06:37:10"),
                    formatter.parse("31-Dec-1997 06:37:10"),
                    true
            );
        });
    }
}