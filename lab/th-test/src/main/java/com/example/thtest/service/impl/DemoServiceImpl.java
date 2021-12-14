package com.example.thtest.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.thtest.model.Demo;
import com.example.thtest.service.DemoService;

public class DemoServiceImpl implements DemoService {

    private ArrayList<Demo> demos = new ArrayList<>();

    @Override
    public void addDemo(String title) {
        this.demos.add(new Demo(this.demos.size(), title));        
    }

    @Override
    public List<Demo> getDemos() {
        return this.demos.subList(0, this.demos.size());
    }
    
    public void generateDemos(int size) {
        for (int i = 0; i < size; i++) {
            this.addDemo(String.format("Demo %d", i + 1));
        }
    }

    private Demo getDemoById(int id) {
        for (Demo demo : this.demos) {
            if (demo.getId() == id) {
                return demo;
            }
        }
        return null;
    }

    @Override
    public void updateDemo(int id) {
        Demo demo = getDemoById(id);

        if (demo != null) {
            int index = this.demos.indexOf(demo);
            this.demos.set(index, new Demo(id, String.format("%s *", demo.getTitle())));        
        }
    }

}
