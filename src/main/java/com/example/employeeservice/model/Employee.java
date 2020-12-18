package com.example.employeeservice.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<WorkingHours> workingHours;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Function function;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date startContract;
    private Date endContract;
    private Boolean active;
    @Column(unique=true)
    private String employeeID = generateEmployeeId();

    private String generateEmployeeId() {
        return "e" + UUID.randomUUID().toString().replace("-", "");
    }

    private Employee() {
    }


    public Employee(List<WorkingHours> workingHours,
                    Function function,
                    String firstName,
                    String lastName,
                    String phoneNumber,
                    Date startContract,
                    Date endContract,
                    Boolean active) {
        this.workingHours = workingHours;
        this.function = function;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.startContract = startContract;
        if (endContract != null) {
            verifyAfterStartContract(endContract);
            this.endContract = endContract;
        }
        this.active = active;
    }

    public void endContract(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("End contract date must be after start contract");
        }
        verifyAfterStartContract(date);

        this.endContract = date;
    }

    public void verifyAfterStartContract(Date date) {
        if (!date.after(this.startContract)) {
            throw new IllegalArgumentException("End contract date must be after start contract");
        }
    }

    public int getId() {
        return id;
    }

    public List<WorkingHours> getWorkingHours() {
        return workingHours;
    }

    public Function getFunction() {
        return function;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getStartContract() {
        return startContract;
    }

    public Date getEndContract() {
        return endContract;
    }

    public Boolean getActive() {
        return active;
    }

    public String getEmployeeID() {
        return employeeID;
    }
}

