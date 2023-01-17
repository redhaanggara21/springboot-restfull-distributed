package com.lsio.springboot.controllers.redis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lsio.springboot.dto.EmployeexDto;
import com.lsio.springboot.services.exception.EmployeeExceptionNotFound;
import com.lsio.springboot.services.redis.EmployeexService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EmployeexController {
    private final EmployeexService employeeService;

    public EmployeexController(EmployeexService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("emp")
    public ResponseEntity<Object> findEmployeeByNo(@RequestParam String empNo) {
        log.info("GET-- employee by emp-no {}", empNo);
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(employeeService.findByEmployeeNo(empNo));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new EmployeeExceptionNotFound("Unexpected error!"));
        }
    }

    @GetMapping("emp/all")
    public ResponseEntity<Object> getAll() {
        log.info("GET-- all employees");
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(employeeService.getAll());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new EmployeeExceptionNotFound("Unexpected error!"));
        }
    }

    @PostMapping("emp")
    public ResponseEntity<Object> updateEmployee(@RequestBody EmployeexDto employeeDto) {
        log.info("UPDATE-- employee with req: {}", employeeDto);
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(employeeService.update(employeeDto, employeeDto.getEmployeeNo()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new EmployeeExceptionNotFound("Unexpected error!"));
        }
    }

    @DeleteMapping("emp/{empNo}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("empNo") String empNo) {
        log.info("DELETE-- employee no {}", empNo);
        try{
            employeeService.delete(empNo);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new EmployeeExceptionNotFound("Unexpected error!"));
        }
    }

}