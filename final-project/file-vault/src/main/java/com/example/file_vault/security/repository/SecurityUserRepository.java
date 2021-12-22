package com.example.file_vault.security.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.file_vault.security.model.SecurityUser;

public interface SecurityUserRepository extends CrudRepository<SecurityUser, Long> {

	public Optional<SecurityUser> findByUsername(String username);
	
	public Optional<SecurityUser> findByToken(String token);
	
	public Optional<SecurityUser> findByUsernameAndPassword(String username, String password);
	
}
