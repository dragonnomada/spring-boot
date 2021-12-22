package com.example.file_vault.security.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecurityUser {

	@Id @GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String username;
	
	@Getter @Setter
	private String password;
	
	@Getter @Setter
	private String token;
	
	@Getter @Setter
	private String roles;
	
	public long calculateExpirationMillis() {
		// TODO: Dependiendo los roles del usuario, calcular el tiempo de expiración
		return 60000l;
	}
	
}
