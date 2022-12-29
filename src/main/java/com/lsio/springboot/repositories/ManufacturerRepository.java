package com.lsio.springboot.repositories;

import com.lsio.springboot.entities.Manufacturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Integer>{
    
}
