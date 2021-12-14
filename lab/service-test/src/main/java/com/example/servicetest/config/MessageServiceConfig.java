package com.example.servicetest.config;

import com.example.servicetest.service.MessageService;
import com.example.servicetest.service.impl.MessageServiceImpl;

import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// @ComponentScan("com.example.servicetest")
public class MessageServiceConfig {
    
    @Bean
    public MessageService messageServiceDemo1() {
        MessageService messageService = new MessageServiceImpl();

        ((MessageServiceImpl)messageService).generateDemo(20);

        return messageService;
    }
    
    @Bean
    public MessageService messageServiceDemo2() {
        MessageService messageService = new MessageServiceImpl();

        ((MessageServiceImpl)messageService).generateDemo(5);

        return messageService;
    }

}
