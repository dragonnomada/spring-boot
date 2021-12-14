package com.example.crud.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.model.Student;
import com.example.crud.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentApiController {

	// Require: Dependency Injection of <StudentService>
	@Autowired
	@Qualifier("studentServicePersitent") // @Qualifier("studentServiceLocal")
	StudentService studentService;
	
	@GetMapping("")
	public List<Student> students() {
		return studentService.getAllStudents();
	}
	
}
