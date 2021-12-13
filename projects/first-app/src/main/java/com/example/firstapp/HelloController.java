package com.example.firstapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {
    
    @GetMapping("/api/hello")
    public String hello() {
        System.out.println("Hello");
        return "Hello Spring Boot - Web";
    }
    
    @GetMapping("/api/hello/world")
    public String helloWorld() {
        System.out.println("Hello world");
        return "<strong style=\"color:red;\">Hello World</strong>";
    }
    
    @GetMapping("/api/hello/message")
    public HelloMessage helloMessage() {
        return new HelloMessage("Hola mundo", new Date(), 2);
    }

}
