package com.lsio.springboot.services.advancedsearching;

import com.lsio.springboot.dao.EmployeeRepository;
import com.lsio.springboot.domain.Employee;
import com.lsio.springboot.dto.EmployeeDto;
import com.lsio.springboot.mapper.DomainDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeeService {

    @Autowired
    private EmployeeRepository empRepository;

    public EmployeeDto addEmployee(EmployeeDto employeeDto){
        Employee emp = DomainDtoMapper.getEmployee(employeeDto);
        return DomainDtoMapper.getEmployeeDto(empRepository.saveAndFlush(emp));
    }

    public List<Employee> findAllEmployee(){
        return empRepository.findAll();
    }

    public Page<Employee> findBySearchCriteria(Specification<Employee> spec, Pageable page){
        Page<Employee> searchResult = empRepository.findAll(spec, page);
        return searchResult;
    }
}
