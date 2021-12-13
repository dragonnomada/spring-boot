package com.example.demo.service.local;

import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.model.Greeting;
import com.example.demo.service.GreetingService;

public class GreetingServiceLocal implements GreetingService {

    private static final String template = "Hello %s";
    private final AtomicLong counter = new AtomicLong();

    public static GreetingService shared = new GreetingServiceLocal();

    @Override
    public Greeting getGreeting(String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
