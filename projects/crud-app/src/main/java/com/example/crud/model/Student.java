package com.example.crud.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Student {
	
	@Id
	private final int id;
	private String name;
	private boolean active;
	
}
