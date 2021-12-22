package com.example.file_vault.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Modificamos la configuración de la seguridad HTTP para implementar JSON Web Tokens
		http.csrf().disable() // Deshabilitamos el CSRF por defecto
			// Agregamos el filtro personalizado de JWT con soporte a roles
			.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			// TODO: Agregar rutas públicas que no requieran token (limitar a recursos públicos y logins)
			.antMatchers(HttpMethod.GET, "/public/**").permitAll()
			.antMatchers(HttpMethod.GET, "/error").permitAll()
			//.antMatchers(HttpMethod.GET, "/admin").hasRole("ROLE_ADMIN")
			//	.and().hasRole("ROLE_USER")
			.antMatchers(HttpMethod.POST, "/secure/login").permitAll()
			.antMatchers(HttpMethod.PUT, "/api/user/new").permitAll()
			.anyRequest().authenticated();			
	}
	
}
