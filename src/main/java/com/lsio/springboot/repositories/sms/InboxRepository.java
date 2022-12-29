package com.lsio.springboot.repositories.sms;

import com.lsio.springboot.entities.sms.Inbox;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboxRepository extends JpaRepository<Inbox,Integer> {
    
}
