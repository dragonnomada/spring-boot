package com.example.file_vault.security.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.file_vault.security.config.JWTAuthorizationFilter;
import com.example.file_vault.security.model.SecurityUser;
import com.example.file_vault.security.repository.SecurityUserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SecurityService {

	@Autowired
	private SecurityUserRepository securityUserRepository;
	
	// TODO: Agregar más servicios de seguridad
	public SecurityUser addUser(String username, String password, String roles) {
		SecurityUser user = SecurityUser.builder()
				.username(username)
				.password(password)
				.roles(roles)
				.token("")
				.build();
		
		return securityUserRepository.save(user);
	}
	
	public boolean isValidUser(String username, String password) {
		// TODO: Validar usuario y contraseña con la base de datos
		Optional<SecurityUser> securityUserOptional = securityUserRepository.findByUsernameAndPassword(username, password);
		
		return securityUserOptional.isPresent();
	}
	
	public SecurityUser getUser(String username) {
		// TODO: Obtener los datos del usuario y sus roles desde la base de datos
		
		Optional<SecurityUser> securityUserOptional = securityUserRepository.findByUsername(username);
		
		if (securityUserOptional.isPresent()) {
			return securityUserOptional.get();
		}
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
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
		securityUserRepository.save(user);
		
		System.out.println(String.format("username: %s | Bearer %s", user.getUsername(), token));

		return JWTAuthorizationFilter.PREFIX + token;
		
	}
	
}
