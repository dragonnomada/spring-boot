package com.example.thtest.controller;

import java.util.List;

import com.example.thtest.model.Demo;
import com.example.thtest.service.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;
    
    @ModelAttribute("title")
    public String title() {
        return "Hello world";
    }

    @ModelAttribute("demos") 
    public List<Demo> demos() {
        return this.demoService.getDemos();
    }

    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }

    @PostMapping("/demo/{id:\\d+}/update")
    public String demoUpdateTitle(@PathVariable("id") int id) {
        System.out.println(id);
        this.demoService.updateDemo(id);
        return "redirect:/demo";
    }
    

}
