package com.example.sec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Getter @Setter
	private String username;
	
	@Getter @Setter
	private String token;
	
	@Getter @Setter
	private String role;
	
}
