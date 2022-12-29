package com.lsio.springboot.services;

import java.util.List;

import com.lsio.springboot.entities.Sms;
import com.lsio.springboot.entities.sms.Failed;
import com.lsio.springboot.entities.sms.Inbox;
import com.lsio.springboot.entities.sms.Other;
import com.lsio.springboot.entities.sms.Sent;
import com.lsio.springboot.repositories.SmsRepository;
import com.lsio.springboot.repositories.sms.FailedRepository;
import com.lsio.springboot.repositories.sms.InboxRepository;
import com.lsio.springboot.repositories.sms.OtherRepository;
import com.lsio.springboot.repositories.sms.SentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Autowired
    SmsRepository smsRepository;

    @Autowired
    InboxRepository inboxRepository;

    @Autowired
    SentRepository sentRepository;

    @Autowired
    FailedRepository failedRepository;

    @Autowired
    OtherRepository otherRepository;

    public SmsService(){


    }

    public Inbox addToInbox(Inbox inbox) {
        return inboxRepository.save(inbox);
    }

    public Sent addToSent(Sent sent) {
        return sentRepository.save(sent);
    }

    public Failed addToFailed(Failed failed) {
        return failedRepository.save(failed);
    }

    public List<Inbox> fetchInbox() {
        return inboxRepository.findAll();
    }

    public List<Sms> fetchAll() {
        return smsRepository.findAll();
    }

    public List<Other> fetchOthers() {
        return otherRepository.findAll();
    }
    
    
}
