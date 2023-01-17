package com.lsio.springboot.services.redis;

import com.lsio.springboot.dto.EmployeexDto;

import java.util.List;

public interface EmployeexService {

    EmployeexDto findByEmployeeNo(String empNo);

    List<EmployeexDto> getAll();

    EmployeexDto update(EmployeexDto employeeDto, String empNo);

    void delete(String empNo);
}
