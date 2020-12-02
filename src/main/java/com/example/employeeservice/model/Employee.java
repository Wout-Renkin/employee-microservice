package com.example.employeeservice.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Werkuren> werkuren;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Functie functie;
    private String voornaam;
    private String achternaam;
    private String gsmNummer;
    private Date startContract;
    private Date eindeContract;
    private Boolean actief;
    @Column(unique=true)
    private String rijksregisternummer;


    public List<Werkuren> getWerkuren() {
        return werkuren;
    }

    public void setWerkuren(List<Werkuren> werkuren) {
        this.werkuren = werkuren;
    }

    public Functie getFunctie() {
        return functie;
    }

    public void setFunctie(Functie functie) {
        this.functie = functie;
    }

    public String getRijksregisternummer() {
        return rijksregisternummer;
    }

    public void setRijksregisternummer(String rijksregisternummer) {
        this.rijksregisternummer = rijksregisternummer;
    }

    private Employee() {
    }

    public Employee(List<Werkuren> werkuren, Functie functie, String voornaam, String achternaam, String gsmNummer, Date startContract, Date eindeContract, Boolean actief, String rijksregisternummer) {
        this.werkuren = werkuren;
        this.functie = functie;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.gsmNummer = gsmNummer;
        this.startContract = startContract;
        this.eindeContract = eindeContract;
        this.actief = actief;
        this.rijksregisternummer = rijksregisternummer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getGsmNummer() {
        return gsmNummer;
    }

    public void setGsmNummer(String gsmNummer) {
        this.gsmNummer = gsmNummer;
    }

    public Date getStartContract() {
        return startContract;
    }

    public void setStartContract(Date startContract) {
        this.startContract = startContract;
    }

    public Date getEindeContract() {
        return eindeContract;
    }

    public void setEindeContract(Date eindeContract) {
        this.eindeContract = eindeContract;
    }

    public Boolean getActief() {
        return actief;
    }

    public void setActief(Boolean actief) {
        this.actief = actief;
    }
}