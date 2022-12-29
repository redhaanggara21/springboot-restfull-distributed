package com.lsio.springboot.repositories;

import com.lsio.springboot.entities.Sms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<Sms,Integer> {
    
}
