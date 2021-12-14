package com.example.crud.service;

import java.util.List;

import com.example.crud.model.Student;

public interface StudentService {

	public List<Student> getAllStudents();
	
	public Student addStudent(int id, String name);
	
}
