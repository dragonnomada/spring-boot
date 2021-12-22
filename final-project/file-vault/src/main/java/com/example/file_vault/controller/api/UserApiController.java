package com.example.file_vault.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.file_vault.model.User;
import com.example.file_vault.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@PutMapping("/new")
	public User addUser(@RequestParam String username, @RequestParam String password) {
		return userService.addUser(username, password);
	}
	
}
