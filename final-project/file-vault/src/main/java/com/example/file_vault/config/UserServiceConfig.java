package com.example.file_vault.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.file_vault.service.UserService;
import com.example.file_vault.service.impl.UserServiceImpl;

@Configuration
public class UserServiceConfig {

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
	
}
