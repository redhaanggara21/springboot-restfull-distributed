package com.lsio.springboot.repositories.redis;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lsio.springboot.entities.Employeex;

import java.util.Optional;

@Repository
public interface EmployeexRepository extends JpaRepository<Employeex, Integer> {

    Optional<Employeex> findFirstByEmployeeNo(String empNo);
}

