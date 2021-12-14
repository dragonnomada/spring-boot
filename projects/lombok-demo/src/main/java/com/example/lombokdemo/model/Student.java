package com.example.lombokdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
public class Student {

	@Getter
	private final int id;
	@Getter
	private String name;
	@Getter
	private String lastName;
	@Getter
	@Setter
	private boolean active;
	
	@Override
	public String toString() {
		return String.format(
				"[%d] %20s | ACTIVE: %B", 
				this.id,
				this.name + " " + this.lastName,
				this.active
		);
	}
	
}
