package com.example.file_vault.service;

import java.util.List;

import com.example.file_vault.model.File;
import com.example.file_vault.model.User;

public interface UserService {

	public User addUser(String username, String password);
	
	public User addFileToUser(User user, File file);
	
	public List<User> getAll();
	
}
