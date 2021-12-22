package com.example.file_vault.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.file_vault.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public List<User> findAll();
	
}
