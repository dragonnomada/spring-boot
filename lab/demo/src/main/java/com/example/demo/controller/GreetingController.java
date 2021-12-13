package com.example.demo.controller;

import com.example.demo.model.Greeting;
import com.example.demo.service.GreetingService;
import com.example.demo.service.local.GreetingServiceLocal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    
    GreetingService greetingServiceShared = GreetingServiceLocal.shared;
    GreetingService greetingService = new GreetingServiceLocal();

    @GetMapping(value = {"", "/"})
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return greetingServiceShared.getGreeting(name);
    }

    @GetMapping("/hey")
    public Greeting greetingHey() {
        return greetingService.getGreeting("hey");
    }

}
