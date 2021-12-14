package com.example.thtest.config;

import com.example.thtest.service.DemoService;
import com.example.thtest.service.impl.DemoServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class DemoServiceConfig {
    
    @Bean
    public DemoService localDemoService() {
        DemoServiceImpl demoService = new DemoServiceImpl();

        demoService.generateDemos(40);

        return (DemoService)demoService;
    }

}
