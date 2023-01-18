package com.lsio.springboot.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supervisor")
public class Supervisor implements Serializable {

    public Supervisor(int i, String string, String string2, String string3, String string4, String string5,
            String string6, java.util.Date hiredDate, String string7, String string8, String string9,
            java.util.Date dob, BigDecimal bigDecimal, double d, double e) {
    }

    private static final long serialVersionUID = 4715702306723909037L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String supervisorNo;

    private String firstName;

    private String midInit;

    private String lastName;

    private String workDept;

    private String phoneNo;

    private Date hireDate;

    private String job;

    private String edLevel;

    private String sex;

    private Date birthDate;

    private BigDecimal salary;

    private Double bonus;

    private Double commission;
}

