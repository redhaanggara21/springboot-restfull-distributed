package com.lsio.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "WORKER")
public class Worker {

    @Id
    @Column(name = "WORK_ID")
    private Long wrkId;

    @Column(name = "WORK_LASTNM")
    private String wrklastNm;

    @Column(name = "WORK_FIRSTNM")
    private String  wrkfirstNm;

    @Column(name = "JOB_NM")
    private String jobNm;

    @Column(name = "MGR_ID", nullable = true)
    private Long managerId;

    @Column(name = "HIREDT")
    private Date hireDt;

    @Column(name = "SALARY")
    private double salary;

    @Column(name = "COMMISSION", nullable = true)
    private double commission;

    @ManyToOne
    @JoinColumn(name = "DEPT_ID")
    private Department department;

}
