package com.lsio.springboot.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lsio.springboot.commons.PersonInfo;

@Entity
@Table(name="doctors")
@AttributeOverride(name = "entrydate", column = @Column(name ="joiningdate"))
public class Doctor extends PersonInfo {
    
    private String roomno;
    private String specialization;

    public String getRoomno() {
        return roomno;
    }
    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    
}
