package com.example.lombokdemo.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@Builder
public class Color {

    private final int id;
    private String color;
    private int red;
    private int green;
    private int blue;
    
    public Color(int id) {
    	this.id = id;
    }
    
    @Override
    public String toString() {
    	return String.format("[%d] %10s", this.id, this.color);
    }
    
}
