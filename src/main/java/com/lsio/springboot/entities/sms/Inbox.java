package com.lsio.springboot.entities.sms;

import javax.persistence.Entity;

import com.lsio.springboot.entities.Sms;

@Entity

public class Inbox extends Sms {

    private String smstype;

    public String getSmstype() {
        return smstype;
    }

    public void setSmstype(String smstype) {
        this.smstype = smstype;
    }

    

}
