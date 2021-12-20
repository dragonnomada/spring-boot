package com.example.app.security.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.example.app.security.config.JWTAuthorizationFilter;
import com.example.app.security.model.SecurityUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SecurityService {

	// TODO: Agregar más servicios de seguridad
	
	public boolean isValid(String username, String password) {
		// TODO: Validar usuario y contraseña con la base de datos
		if (username.equals("batman") || username.equals("robin") || username.equals("alfred")) {
			if (password.equals("baticueva")) {
				return true;
			}
		}
		
		return false;
	}
	
	public SecurityUser getUser(String username) {
		// TODO: Obtener los datos del usuario y sus roles desde la base de datos
		
		String roles = "";
		
		if (username.equals("batman")) {
			roles = "ROLE_ADMIN";
		} else if (username.equals("robin")) {
			roles = "ROLE_USER";
		} else if (username.equals("alfred")) {
			roles = "ROLE_ADMIN,ROLE_USER";
		}
		
		SecurityUser user = SecurityUser.builder()
			.username(username)
			.roles(roles)
			.build();
		
		return user;
	}
	
	public String generateToken(SecurityUser user) {
		
		// TODO: Modificar la forma de generar el token
		
		String secretKey = JWTAuthorizationFilter.SECRET;
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(user.getRoles());
		
		String token = Jwts
				.builder()
				.setId("com.example")
				.setSubject(user.getUsername())
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + user.calculateExpirationMillis()))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();
		
		user.setToken(token);

		// TODO: Actualizar en base de datos el nuevo token del usuario
		
		System.out.println(String.format("username: %s | Bearer %s", user.getUsername(), token));

		return JWTAuthorizationFilter.PREFIX + token;
		
	}
	
}
