package com.example.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfig {

	@Bean
	public UserService userSevice() {
		// TODO: Configurar el servicio real
		System.out.println("Bean UserService userSevice [REAL]");
		return new UserServiceImpl();
	}
	
}
