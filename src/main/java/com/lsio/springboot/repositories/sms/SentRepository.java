package com.lsio.springboot.repositories.sms;

import com.lsio.springboot.entities.sms.Sent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SentRepository extends JpaRepository<Sent,Integer>{
    
}
