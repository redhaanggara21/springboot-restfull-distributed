package com.lsio.springboot.dto.mapper;

import com.lsio.springboot.dto.DepartmentDto;
import com.lsio.springboot.dto.WorkerDto;
import com.lsio.springboot.entities.domain.Department;
import com.lsio.springboot.entities.domain.SalaryGrade;
import com.lsio.springboot.entities.domain.Worker;
import com.lsio.springboot.dto.SalaryGradeDto;
import org.modelmapper.ModelMapper;

public class DomainDtoMapper {

    public static Worker getEmployee(WorkerDto empDto){
        if(empDto == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(empDto, Worker.class);
    }

    public static WorkerDto getEmployeeDto(Worker emp){
        if(emp == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(emp, WorkerDto.class);
    }

    public static Department getDepartment(DepartmentDto deptDto){
        if(deptDto == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(deptDto, Department.class);
    }

    public static DepartmentDto getDepartmentDto(Department dept){
        if(dept == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dept, DepartmentDto.class);
    }

    public static SalaryGrade getSalaryGrade(SalaryGradeDto salGradeDto){
        if(salGradeDto == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(salGradeDto, SalaryGrade.class);
    }

    public static SalaryGradeDto getSalaryGradeDto(SalaryGrade salGrade){
        if(salGrade == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(salGrade, SalaryGradeDto.class);
    }
}
