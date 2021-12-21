package com.example.server;

import java.util.List;
import java.util.Optional;

public interface UserService {

	public User saveUser(String name, int age);
	
	public Iterable<User> getAllUsers();
	
	public Optional<User> findUserByName(String name);
	
	public List<User> findByAgeBetween(int min, int max);
	
}
