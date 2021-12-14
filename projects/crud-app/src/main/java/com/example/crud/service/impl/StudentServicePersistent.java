package com.example.crud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.crud.model.Student;
import com.example.crud.repository.StudentRepository;
import com.example.crud.service.StudentService;

public class StudentServicePersistent implements StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	//@Autowired
	//SchoolService shoolService;
	
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student addStudent(int id, String name) {
		Student student = Student.builder()
				.id(id)
				.name(name)
				.build();
		
		studentRepository.save(student);
		
		return student;
	}

}
