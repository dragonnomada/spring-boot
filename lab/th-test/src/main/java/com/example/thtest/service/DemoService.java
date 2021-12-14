package com.example.thtest.service;

import java.util.List;

import com.example.thtest.model.Demo;

public interface DemoService {
    
    public void addDemo(String title);

    public List<Demo> getDemos();

    public void updateDemo(int id);

}
