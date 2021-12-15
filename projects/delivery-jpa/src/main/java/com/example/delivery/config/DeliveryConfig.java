package com.example.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.delivery.service.DeliveryService;
import com.example.delivery.service.impl.DeliverServiceImpl;

@Configuration
public class DeliveryConfig {

	@Bean
	public DeliveryService deliverService() {
		return new DeliverServiceImpl();
	}
	
}
