package com.example.employeeservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Function {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String functionName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Function(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    private Function() {
    }
}
