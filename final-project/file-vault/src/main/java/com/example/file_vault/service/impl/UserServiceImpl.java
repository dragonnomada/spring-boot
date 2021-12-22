package com.example.file_vault.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.file_vault.model.User;
import com.example.file_vault.repository.UserRepository;
import com.example.file_vault.security.model.SecurityUser;
import com.example.file_vault.security.service.SecurityService;
import com.example.file_vault.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private SecurityService securityService;
	
	@Override
	public User addUser(String username, String password) {
		
		SecurityUser securityUser = securityService.addUser(username, password, "ROLE_USER");
		
		User user = User.builder()
				.securityUser(securityUser)
				.build();
		
		return userRepository.save(user);
	}

	
	
}
