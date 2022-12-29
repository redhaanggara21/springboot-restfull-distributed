package com.lsio.springboot.entities.vehicle;

import javax.persistence.Entity;

import com.lsio.springboot.entities.Vehicle;

@Entity

public class Car extends Vehicle {

    private String seatingcapacity;
    private String sunroof;
    public String getSeatingcapacity() {
        return seatingcapacity;
    }
    public void setSeatingcapacity(String seatingcapacity) {
        this.seatingcapacity = seatingcapacity;
    }
    public String getSunroof() {
        return sunroof;
    }
    public void setSunroof(String sunroof) {
        this.sunroof = sunroof;
    }

    
    
}
