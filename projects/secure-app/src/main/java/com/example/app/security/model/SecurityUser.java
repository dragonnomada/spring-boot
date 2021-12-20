package com.example.app.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecurityUser {

	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String username;
	
	@Getter @Setter
	private String token;
	
	@Getter @Setter
	private String roles;
	
	public long calculateExpirationMillis() {
		// TODO: Dependiendo los roles del usuario, calcular el tiempo de expiración
		return 60000l;
	}
	
}
