package com.example.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.crud.service.StudentService;
import com.example.crud.service.impl.StudentServiceLocal;
import com.example.crud.service.impl.StudentServicePersistent;

@Configuration
public class StudentConfig {

	@Bean
	public StudentService studentServiceLocal() {
		StudentServiceLocal studentServiceLocal = new StudentServiceLocal();
		
		for (int i = 0; i < 10; i++) {
			studentServiceLocal.addStudent(i + 1, String.format("Student %d", i + 1));
		}
		
		return (StudentService)studentServiceLocal; // Upgrading
	}
	
	@Bean
	public StudentService studentServicePersistent() {
		StudentServicePersistent studentServicePersistent = new StudentServicePersistent();
		
		for (int i = 0; i < 20; i++) {
			studentServicePersistent.addStudent(i + 1, String.format("JPA Student %d", i + 1));
		}
		
		return (StudentService)studentServicePersistent;
	}
	
}
