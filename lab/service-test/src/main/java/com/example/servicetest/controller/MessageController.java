package com.example.servicetest.controller;

import java.util.List;

import com.example.servicetest.model.Message;
import com.example.servicetest.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    
    @Autowired
    @Qualifier("messageServiceDemo2")
    MessageService messageService;

    @GetMapping("")
    public List<Message> getMessages() {
        return messageService.getMessages();
    }

}
