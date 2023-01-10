package com.lsio.springboot.repositories.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lsio.springboot.entities.domain.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
