package com.lsio.springboot.services.redis;

import com.lsio.springboot.dto.EmployeexDto;
import com.lsio.springboot.entities.Employeex;
import com.lsio.springboot.repositories.redis.EmployeexRepository;
import com.lsio.springboot.services.exception.EmployeeExceptionNotFound;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeexServiceImpl implements EmployeexService {

    private final EmployeexRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeexServiceImpl(EmployeexRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Cacheable(value = "EmployeeDto", key = "#empNo")
    @Override
    public EmployeexDto findByEmployeeNo(String empNo) {
        log.info("Fetch employee from db by emp-no {}", empNo);
        Optional<Employeex> employee = employeeRepository.findFirstByEmployeeNo(empNo);
        return employee.map(value ->
                modelMapper.map(value, EmployeexDto.class))
                .orElseThrow(() -> new EmployeeExceptionNotFound("Employee not found!"));
    }

    @Cacheable(value = "EmployeeDtoList")
    @Override
    public List<EmployeexDto> getAll() {
        log.info("Fetch all employees from db");
        List<Employeex> employees = employeeRepository.findAll();
        if (!employees.isEmpty()) {
            return employees.stream()
                    .map(employee -> modelMapper.map(employee, EmployeexDto.class))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Caching(put = {
            @CachePut(value = "EmployeeDto", key = "#empNo")},
            evict = {@CacheEvict(value = "EmployeeDtoList", allEntries = true)}
    )
    @Override
    public EmployeexDto update(EmployeexDto employeeDto, String empNo) {
        Optional<Employeex> employee = employeeRepository.findFirstByEmployeeNo(empNo);
        if (employee.isPresent()) {
            employee.get().setSex(employeeDto.getSex());
            employee.get().setEdLevel(employeeDto.getEdLevel());
            Employeex tempEmp = employeeRepository.save(employee.get());
            return modelMapper.map(tempEmp, EmployeexDto.class);
        }
        log.error("No employee no {} found", empNo);
        return null;
    }

    @Caching(evict = {
            @CacheEvict(value = "EmployeeDtoList", allEntries = true),
            @CacheEvict(value = "EmployeeDto", key = "#empNo")
    })
    @Override
    public void delete(String empNo) {
        Optional<Employeex> employee = employeeRepository.findFirstByEmployeeNo(empNo);
        employee.ifPresent(employeeRepository::delete);
    }
}
