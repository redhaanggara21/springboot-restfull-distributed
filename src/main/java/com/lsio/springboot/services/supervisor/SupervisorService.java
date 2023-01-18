package com.lsio.springboot.services.supervisor;

import java.util.List;

import com.lsio.springboot.dto.SuperviserDto;
import com.lsio.springboot.entities.Supervisor;

public interface SupervisorService {
    SuperviserDto findBySupervisorNo(String empNo);
    List<SuperviserDto> getAll();
    SuperviserDto update(SuperviserDto superviserDto, String empNo);
    Supervisor save(Supervisor superviserDto);
    void delete(String empNo);
}
