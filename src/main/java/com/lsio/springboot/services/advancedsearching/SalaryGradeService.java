package com.lsio.springboot.services.advancedsearching;

import com.lsio.springboot.dao.SalaryGradeRepository;
import com.lsio.springboot.domain.SalaryGrade;
import com.lsio.springboot.dto.SalaryGradeDto;
import com.lsio.springboot.mapper.DomainDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryGradeService {

    @Autowired
    private SalaryGradeRepository salaryGradeRepository;

    public SalaryGradeDto addSalaryGrade(SalaryGradeDto salaryGradeDtoDto){
        SalaryGrade salGrade = DomainDtoMapper.getSalaryGrade(salaryGradeDtoDto);
        return DomainDtoMapper.getSalaryGradeDto(salaryGradeRepository.saveAndFlush(salGrade));
    }
}
