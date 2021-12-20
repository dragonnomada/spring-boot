package com.example.cont;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Counter {

	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String namespace;
	
	@Getter @Setter
	private Integer value;
	
}
