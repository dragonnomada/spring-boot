package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemServiceConfig {

	@Bean
	public ItemService itemService() {
		System.out.println("Usando Real");
		ItemService itemService = new ItemServiceImpl();
		
		itemService.addItem("Test A");
		itemService.addItem("Test B");
		itemService.addItem("Test C");
		
		return itemService;
	}
	
}
