package com.example.server;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(String name, int age) {
		if (age <= 0) return null;
		
		User user = User.builder()
				.name(name)
				.age(age)
				.build();
		
		return userRepository.save(user);
	}

	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findUserByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public List<User> findByAgeBetween(int min, int max) {
		if (min > max) return userRepository.findByAgeBetween(max, min);
		
		return userRepository.findByAgeBetween(min, max);
	}

}
