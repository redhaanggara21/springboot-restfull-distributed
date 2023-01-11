package com.lsio.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import com.lsio.springboot.entities.domain.Department;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    private Long wrkId;
    private String wrklastNm;
    private String wrkfirstNm;
    private String jobNm;
    private Long managerId;
    private Date hireDt;
    private double salary;
    private double commission;
    private Department department;
}
