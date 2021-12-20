package com.example.app.controller.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// origins:
// * - Todos
// IP1,IP2,... - IP específicas
// mycompany.com,... - Dominios específicos

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class MyApiController {

	//@PreAuthorize("permitAll()")
	@GetMapping("/hello")
	public String getHello() {
		return "Hello world";
	}
	
	//@PreAuthorize("hasRole('ROLE_ADMIN') and hasAuthority('com.example')")
	//@PostAuthorize("hasRole('ROLE_DELIVERY') and hasAuthority('com.dhl')")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public String getHelloAdmin() {
		return "Hola administrador";
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/user")
	public String getHelloUser() {
		return "Hola usuario";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') AND hasRole('ROLE_USER')")
	@GetMapping("/super")
	public String getHelloSuper() {
		return "Hola super usuario (administrador y usuario)";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	@GetMapping("/generic")
	public String getHelloAdminUser() {
		return "Hola usuario normal o administrador";
	}
	
	/*@PostAuthorize
	  ("returnObject.username == authentication.principal.username")
	public SecurityUser refreshToken(@RequestParam String username) {
		...
		return user;
	}*/
	
}
