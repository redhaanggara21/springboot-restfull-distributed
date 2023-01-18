package com.lsio.springboot.services.supervisor;

import java.util.Optional;
import org.modelmapper.ModelMapper;

import com.lsio.springboot.dto.SuperviserDto;
import com.lsio.springboot.entities.Supervisor;
import com.lsio.springboot.repositories.SupervisorRepository;
import com.lsio.springboot.services.exception.SupervisorExceptionNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SupervisorServiceImpl implements SupervisorService {

    private final SupervisorRepository supervisorRepository;
    private final ModelMapper modelMapper;

    public SupervisorServiceImpl(
                                    SupervisorRepository supervisorRepository, 
                                    ModelMapper modelMapper
                                ) {
        this.supervisorRepository = supervisorRepository;
        this.modelMapper = modelMapper;
    }

    // @Cacheable(value = "SupervisorDto", key ="#supNo")
    @Override
    public SuperviserDto findBySupervisorNo(String supNo) {
        log.info("Fetch supervisor from db by sup-no {}", supNo);
        Optional<Supervisor> supervisor = supervisorRepository.findFirstBySupervisorNo(supNo);
        return supervisor.map(value ->
                modelMapper.map(value, SuperviserDto.class))
                .orElseThrow(() -> new SupervisorExceptionNotFound("Supervisor not found!"));
    }

    // @Cacheable(value = "SupervisorDtoList")
    @Override
    public List<SuperviserDto> getAll() {
        log.info("Fetch all supervisor from db");
        List<Supervisor> supervisors = supervisorRepository.findAll();
        if (!supervisors.isEmpty()) {
            return supervisors.stream()
                    .map(superv -> modelMapper.map(superv, SuperviserDto.class))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public SuperviserDto update(SuperviserDto supervisorDto, String empNo) {
        Optional<Supervisor> supervisor =  supervisorRepository.findFirstBySupervisorNo(empNo);
        if (supervisor.isPresent()) {
            supervisor.get().setSex(supervisorDto.getSex());
            supervisor.get().setEdLevel(supervisorDto.getEdLevel());
            Supervisor tempEmp = supervisorRepository.save(supervisor.get());
            return modelMapper.map(tempEmp, SuperviserDto.class);
        }
        log.error("No supervisor no {} found", empNo);
        return null;
    }

    @Override
    public Supervisor save(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    @Override
    public void delete(String empNo) {
        Optional<Supervisor> employee = supervisorRepository.findFirstBySupervisorNo(empNo);
        employee.ifPresent(supervisorRepository::delete);
    }
}

