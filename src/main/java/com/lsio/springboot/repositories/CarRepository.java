package com.lsio.springboot.repositories;

import com.lsio.springboot.entities.vehicle.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer>{
    
}
