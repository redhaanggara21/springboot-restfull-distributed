package com.lsio.springboot.entities;

import java.time.ZonedDateTime;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="sms")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "smsfolder")
public class Sms {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fromorto;
    private String messagetext;
    private ZonedDateTime sentorreceivedon;

    //#region Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFromorto() {
        return fromorto;
    }
    public void setFromorto(String fromorto) {
        this.fromorto = fromorto;
    }
    public String getMessagetext() {
        return messagetext;
    }
    public void setMessagetext(String messagetext) {
        this.messagetext = messagetext;
    }
    public ZonedDateTime getSentorreceivedon() {
        return sentorreceivedon;
    }
    public void setSentorreceivedon(ZonedDateTime sentorreceivedon) {
        this.sentorreceivedon = sentorreceivedon;
    }
    //#endregion
    
}
