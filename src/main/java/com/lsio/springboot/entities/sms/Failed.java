package com.lsio.springboot.entities.sms;

import javax.persistence.Entity;

import com.lsio.springboot.entities.Sms;

@Entity

public class Failed  extends Sms {

    private String errormessage;

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    
}
