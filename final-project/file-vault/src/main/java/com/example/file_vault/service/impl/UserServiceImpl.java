package com.example.file_vault.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.file_vault.model.File;
import com.example.file_vault.model.User;
import com.example.file_vault.repository.FileRepository;
import com.example.file_vault.repository.UserRepository;
import com.example.file_vault.security.model.SecurityUser;
import com.example.file_vault.security.service.SecurityService;
import com.example.file_vault.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private SecurityService securityService;
	
	@Autowired 
	private FileRepository fileRepository;
	
	@Override
	public User addUser(String username, String password) {
		
		SecurityUser securityUser = securityService.addUser(username, password, "ROLE_USER");
		
		User user = User.builder()
				.securityUser(securityUser)
				.build();
		
		return userRepository.save(user);
	}

	@Override
	public User addFileToUser(User user, File file) {	
		file.setUser(user);
		
		File fileSaved = fileRepository.save(file);
		
		List<File> files = user.getFiles();
		
		if (files == null) {
			files = new ArrayList<File>();
		}
		
		files.add(fileSaved);
		
		user.setFiles(files);
		
		return userRepository.save(user);
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	
	
}
