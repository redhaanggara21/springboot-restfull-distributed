package com.lsio.springboot.dto;

import com.lsio.springboot.domain.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
