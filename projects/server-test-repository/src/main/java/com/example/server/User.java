package com.example.server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	public static User defaultUser() {
		return User.builder().name("Unknown").age(18).build();
	}

	@Id @GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private Integer age;
	
	@Override
	public String toString() {
		return String.format("[%d] %s (%d)", id, name, age);
	}
	
}
