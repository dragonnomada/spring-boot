package com.example.sec;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloApiController {

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/hello")
	public String getHello(HttpServletRequest request) {
		return "Hello";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/world")
	public String getWorld(HttpServletRequest request) {
		return "World";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	@GetMapping("/you")
	public String getYou() {
		return "Hey you";
	}
	
}
