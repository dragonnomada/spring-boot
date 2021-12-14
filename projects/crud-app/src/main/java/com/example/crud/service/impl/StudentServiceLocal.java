package com.example.crud.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.crud.model.Student;
import com.example.crud.service.StudentService;

public class StudentServiceLocal implements StudentService {

	private ArrayList<Student> students = new ArrayList<>();
	
	@Override
	public List<Student> getAllStudents() {
		return students.subList(0, students.size());
	}

	@Override
	public Student addStudent(int id, String name) {
		Student student = Student.builder()
				.id(id)
				.name(name)
				.active(true)
				.build();
		
		students.add(student);
		
		return student;
	}
	
	

}
