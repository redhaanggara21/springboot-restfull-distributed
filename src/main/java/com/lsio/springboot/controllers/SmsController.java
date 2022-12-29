package com.lsio.springboot.controllers;

import java.util.List;

import com.lsio.springboot.entities.Sms;
import com.lsio.springboot.entities.sms.Failed;
import com.lsio.springboot.entities.sms.Inbox;
import com.lsio.springboot.entities.sms.Other;
import com.lsio.springboot.entities.sms.Sent;
import com.lsio.springboot.services.SmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sms/")
public class SmsController {
    
    @Autowired
    SmsService smsService;

    @PostMapping("addtoinbox")
    public Inbox addToInbox(@RequestBody Inbox inbox){
        return smsService.addToInbox(inbox);
    }

    @PostMapping("addtosent")
    public Sent addToSent(@RequestBody Sent sent){
        return smsService.addToSent(sent);
    }

    @PostMapping("addtofailed")
    public Failed addToFailed(@RequestBody Failed failed){
        return smsService.addToFailed(failed);
    }

    @GetMapping("inbox")
    public List<Inbox> fetchInbox(){
        return smsService.fetchInbox();
    }

    @GetMapping("others")
    public List<Other> fetchOthers(){
        return smsService.fetchOthers();
    }

    @GetMapping("allsms")
    public List<Sms> allSms(){
        return smsService.fetchAll();
    }
}
