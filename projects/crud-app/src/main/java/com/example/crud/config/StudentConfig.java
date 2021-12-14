package com.example.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.crud.service.StudentService;
import com.example.crud.service.impl.StudentServiceLocal;

@Configuration
public class StudentConfig {

	@Bean
	public StudentService studentService() {
		StudentServiceLocal studentServiceLocal = new StudentServiceLocal();
		
		for (int i = 0; i < 10; i++) {
			studentServiceLocal.addStudent(i + 1, String.format("Student %d", i + 1));
		}
		
		return (StudentService)studentServiceLocal; // Upgrading
	}
	
}
