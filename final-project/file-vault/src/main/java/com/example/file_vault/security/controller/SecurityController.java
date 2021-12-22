package com.example.file_vault.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.file_vault.security.model.SecurityUser;
import com.example.file_vault.security.service.SecurityService;

@RestController
@RequestMapping("/secure")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class SecurityController {

	@Autowired
	SecurityService securityService;
	
	//@PreAuthorize("permitAll()")
	@PostMapping("/login")
	public SecurityUser signIn(@RequestParam String username, @RequestParam String password) {
		// TODO: Validar si el usuario y contraseña son correctas (Hint: Usar base de datos)
		if (securityService.isValidUser(username, password)) {			
			// Si el usuario es válido, le generamos un token
			
			// Este usuario debería venir de un reporitorio desde la base de datos a través de otro servicio
			SecurityUser user = securityService.getUser(username);
			
			securityService.generateToken(user);
			
			return user;
		}
		
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "El usuario " + username + " no es válido");
	}
	
}
