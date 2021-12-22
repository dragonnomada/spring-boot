package com.example.file_vault.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.file_vault.service.UserService;
import com.example.file_vault.service.impl.UserServiceImpl;
import com.example.file_vault.storage.FileSystemStorageService;
import com.example.file_vault.storage.StorageProperties;
import com.example.file_vault.storage.StorageService;

@Configuration
public class UserServiceConfig {

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
	
	@Bean
	public StorageService storageService() {
		return new FileSystemStorageService(new StorageProperties());
	}
	
}
