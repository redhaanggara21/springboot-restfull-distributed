package com.lsio.springboot.repositories.sms;

import com.lsio.springboot.entities.sms.Other;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherRepository extends JpaRepository<Other,Integer> {
    
}
