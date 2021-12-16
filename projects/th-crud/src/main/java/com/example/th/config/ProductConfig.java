package com.example.th.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.th.service.ProductService;
import com.example.th.service.impl.ProductServiceImpl;

@Configuration
public class ProductConfig {

	@Bean
	public ProductService productService() {
		return new ProductServiceImpl();
	}
	
}
