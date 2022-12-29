package com.lsio.springboot.entities.sms;

import java.time.ZonedDateTime;

import javax.persistence.Entity;

import com.lsio.springboot.entities.Sms;

@Entity
public class Sent extends Sms {
    
    private ZonedDateTime deliveredon;

    public ZonedDateTime getDeliveredon() {
        return deliveredon;
    }

    public void setDeliveredon(ZonedDateTime deliveredon) {
        this.deliveredon = deliveredon;
    }

    
}
