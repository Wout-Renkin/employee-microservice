package com.example.employeeservice;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.Function;
import com.example.employeeservice.model.WorkingHours;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class EmployeeTestFactory {
    private final static SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    public static Employee employee1 = new Employee(
            Arrays.asList(
                    new WorkingHours(
                            getDate("31-Dec-1998 06:37:50"),
                            getDate("31-Dec-1998 17:37:50")
                    ),
                    new WorkingHours(
                            getDate("01-Jan-1999 06:37:50"),
                            getDate("01-Jan-1999 17:37:50")
                    )),
            new Function("BAAS"),
            "Jan",
            "Vermeulen",
            "0495898989",
            getDate("31-Dec-1998 06:37:10"),
            null,
            true
    );

    public static Employee employee2 = new Employee(
            Arrays.asList(
                    new WorkingHours(
                            getDate("31-Dec-1998 06:37:50"),
                            getDate("31-Dec-1998 17:37:50")
                    ),
                    new WorkingHours(
                            getDate("01-Jan-1999 06:37:50"),
                            getDate("01-Jan-1999 17:37:50")
                    )),
            new Function("CEO"),
            "Wout",
            "Renkin",
            "05770523",
            getDate("31-Dec-1998 06:37:10"),
            null,
            true
    );

    public static Employee activeEmployee = new Employee(
            Arrays.asList(
                    new WorkingHours(
                            getDate("31-Dec-1998 06:37:50"),
                            getDate("31-Dec-1998 17:37:50")
                    ),
                    new WorkingHours(
                            getDate("01-Jan-1999 06:37:50"),
                            getDate("01-Jan-1999 17:37:50")
                    )),
            new Function("Mechanic"),
            "Jeff",
            "Schellens",
            "04454333",
            getDate("31-Dec-1998 06:37:10"),
            null,
            true
    );

    public static Employee inactiveEmployee = new Employee(
            Arrays.asList(
                    new WorkingHours(
                            getDate("31-Dec-1998 06:37:50"),
                            getDate("31-Dec-1998 17:37:50")
                    ),
                    new WorkingHours(
                            getDate("01-Jan-1999 06:37:50"),
                            getDate("01-Jan-1999 17:37:50")
                    )),
            new Function("Mechanic"),
            "Senne",
            "Castermans",
            "047705803",
            getDate("31-Dec-1998 06:37:10"),
            null,
            false
    );

    public static Date getDate(String date) {
        try {
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            return formatter.parse(date);
        } catch (ParseException e) {
            // wrap into runtime
            throw new RuntimeException(e);
        }
    }

}
