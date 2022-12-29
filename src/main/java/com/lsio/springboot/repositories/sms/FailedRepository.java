package com.lsio.springboot.repositories.sms;

import com.lsio.springboot.entities.sms.Failed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedRepository extends JpaRepository<Failed,Integer> {
    
}
