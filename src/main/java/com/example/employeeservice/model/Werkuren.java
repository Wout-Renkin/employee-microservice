package com.example.employeeservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Werkuren {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date startTijdstip;
    private Date stopTijdstip;

    public int getId() {
        return id;
    }

    public Werkuren(Date startTijdstip, Date stopTijdstip) {
        this.startTijdstip = startTijdstip;
        this.stopTijdstip = stopTijdstip;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartTijdstip() {
        return startTijdstip;
    }

    public void setStartTijdstip(Date startTijdstip) {
        this.startTijdstip = startTijdstip;
    }

    public Date getStopTijdstip() {
        return stopTijdstip;
    }

    public void setStopTijdstip(Date stopTijdstip) {
        this.stopTijdstip = stopTijdstip;
    }

    private Werkuren(){}

}
