package com.example.crud.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.model.Student;

@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	

}
