package com.example.file_vault.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.file_vault.security.model.SecurityUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id @GeneratedValue
	@Getter @Setter
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "security_user_id")
	@Getter @Setter
	private SecurityUser securityUser;
	
	@OneToMany(
		mappedBy = "user", 
		cascade=CascadeType.ALL, fetch = FetchType.LAZY
	)
	@Getter @Setter
	private List<File> files;
	
	public String getUsername() {
		return securityUser.getUsername();
	}
	
}
