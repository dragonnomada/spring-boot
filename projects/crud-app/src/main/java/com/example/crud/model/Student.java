package com.example.crud.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
	
	private final int id;
	private String name;
	private boolean active;
	
}
