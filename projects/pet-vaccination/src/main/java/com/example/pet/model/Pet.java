package com.example.pet.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pet {

	private final long id;
	private String name;
	private String kind;
	private boolean vaccinated;
	
}
