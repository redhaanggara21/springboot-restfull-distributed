package com.lsio.springboot.services.advancedsearching;

import com.lsio.springboot.dao.DepartmentRepository;
import com.lsio.springboot.domain.Department;
import com.lsio.springboot.dto.DepartmentDto;
import com.lsio.springboot.mapper.DomainDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository deptRepository;

    public DepartmentDto addDepartment(DepartmentDto departmentDto){
        Department dept = DomainDtoMapper.getDepartment(departmentDto);
        return DomainDtoMapper.getDepartmentDto(deptRepository.saveAndFlush(dept));
    }
}
