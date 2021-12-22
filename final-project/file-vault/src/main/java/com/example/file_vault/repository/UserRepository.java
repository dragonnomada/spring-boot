package com.example.file_vault.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.file_vault.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	
	
}
