package com.lsio.springboot.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lsio.springboot.commons.PersonInfo;

@Entity
@Table(name="patients")
@AttributeOverride(name = "entrydate", column = @Column(name ="visitdate"))
public class Patient extends PersonInfo {
    
    private String symptoms;
    private String healthhistory;
    
    public String getSymptoms() {
        return symptoms;
    }
    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
    public String getHealthhistory() {
        return healthhistory;
    }
    public void setHealthhistory(String healthhistory) {
        this.healthhistory = healthhistory;
    }

    
}
