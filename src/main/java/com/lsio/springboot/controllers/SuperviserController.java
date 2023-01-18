package com.lsio.springboot.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lsio.springboot.dto.SuperviserDto;
import com.lsio.springboot.entities.Supervisor;
import com.lsio.springboot.services.exception.SupervisorExceptionNotFound;
import com.lsio.springboot.services.supervisor.SupervisorService;
import com.lsio.springboot.services.supervisor.SupervisorServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/superviser")
@Slf4j
public class SuperviserController {


    private final SupervisorService service;

    public SuperviserController(SupervisorService service) {
        this.service = service;
    }

    // Read operation
    @GetMapping("/")
    public ResponseEntity<Object>list() { 
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                        service.getAll()
                    );
        }catch (Exception e) {
            return ResponseEntity.status(
                    HttpStatus.EXPECTATION_FAILED
                ).body(
                    new SupervisorExceptionNotFound("Unexpected error!")
                );
        }
    }

    @Cacheable(value = "supervisers", key = "#id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("empNo") String empNo) {
        log.info("DELETE-- superviser no {}", empNo);
        try{
            service.delete(empNo);
            return ResponseEntity.status(HttpStatus.OK).body("");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new SupervisorExceptionNotFound("Unexpected error!"));
        }
    }

    @CachePut(value = "supervisers", key = "#supervisers.id")
    // Save operation
    @PostMapping("/")
    public Supervisor save(@Valid @RequestBody Supervisor supervisor) {
        try {
            return service.save(supervisor);
        } catch (Exception e) {
            return null;
        }
    }

    @CachePut(value = "supervisor",  key = "#id")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody SuperviserDto supervisor, @PathVariable("id") String id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.update(supervisor, id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new SupervisorExceptionNotFound("Unexpected error!"));
        }
    }

    // Delete operation
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("empNo") String empNo) {
        log.info("DELETE-- employee no {}", empNo);
        try{
            service.delete(empNo);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new SupervisorExceptionNotFound("Unexpected error!"));
        }
    }
}
