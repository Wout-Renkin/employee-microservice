package com.example.employeeservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Functie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String functieNaam;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Functie(String functieNaam) {
        this.functieNaam = functieNaam;
    }

    public String getFunctieNaam() {
        return functieNaam;
    }

    public void setFunctieNaam(String functieNaam) {
        this.functieNaam = functieNaam;
    }

    private Functie() {
    }
}
