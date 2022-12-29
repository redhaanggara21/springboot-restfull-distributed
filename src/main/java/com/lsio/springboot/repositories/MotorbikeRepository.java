package com.lsio.springboot.repositories;

import com.lsio.springboot.entities.vehicle.Motorbike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorbikeRepository extends JpaRepository<Motorbike,Integer> {
    
}
