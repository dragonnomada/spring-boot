package com.example.sec;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/sec")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class SecureApiController {
	
	@GetMapping("/user")
	public User testUserWithError(@RequestParam String username) {
		// ResponseStatusException(HttpStatus.XX, message, ERROR)
		
		if (username.equals("u500")) {
			throw new ResponseStatusException(
			           HttpStatus.INTERNAL_SERVER_ERROR, "User invalid 500", new Error("xx"));
		} else if (username.equals("u404")) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "User invalid 404");
		} else if (username.equals("u406")) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_ACCEPTABLE, "User invalid 406");
		}
		
		return User.builder().username(username).build();
	}
	

	@PostMapping("/login")
	public User login(@RequestParam String username, @RequestParam String password) {
		
		// TODO: Validate user name and password
		
		String role = "UNKNOWN";
		
		if (username.equals("batman")) {
			role = "ROLE_ADMIN";
		} else if (username.equals("robin")) {
			role = "ROLE_USER";
		} else if (username.equals("alfred")) {
			role = "ROLE_ADMIN,ROLE_USER";
		}
		
		String token = getJWTToken(username, role);
		
		User user = User.builder()
				.username(username)
				.token(token)
				.role(role)
				.build();		
		
		return user;
		
	}
	
	private String getJWTToken(String username, String role) {
		String secretKey = "mySecretKey";
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(role);
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();
		
		System.out.println(String.format("username: %s | Bearer %s", username, token));

		return "Bearer " + token;
	}
	
}
