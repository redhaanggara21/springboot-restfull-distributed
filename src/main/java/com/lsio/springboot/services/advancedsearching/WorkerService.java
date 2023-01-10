package com.lsio.springboot.services.advancedsearching;

import com.lsio.springboot.dto.WorkerDto;
import com.lsio.springboot.entities.domain.Worker;
import com.lsio.springboot.mapper.DomainDtoMapper;
import com.lsio.springboot.repositories.dao.WorkerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository empRepository;

    public WorkerDto addWorker(WorkerDto workerDto){
        Worker emp = DomainDtoMapper.getEmployee(workerDto);
        return DomainDtoMapper.getEmployeeDto(empRepository.saveAndFlush(emp));
    }

    public List<Worker> findAllWorker(){
        return empRepository.findAll();
    }

    public Page<Worker> findBySearchCriteria(Specification<Worker> spec, Pageable page){
        Page<Worker> searchResult = empRepository.findAll(spec, page);
        return searchResult;
    }
}
