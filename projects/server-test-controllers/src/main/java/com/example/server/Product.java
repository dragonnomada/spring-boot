package com.example.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String sku;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private Double price;
	
}
