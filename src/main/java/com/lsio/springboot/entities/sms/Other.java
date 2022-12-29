package com.lsio.springboot.entities.sms;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.lsio.springboot.entities.Sms;

@Entity
@DiscriminatorValue(value = "not null")
public class Other extends Sms {
    
}
