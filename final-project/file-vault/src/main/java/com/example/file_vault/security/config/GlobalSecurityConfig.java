package com.example.file_vault.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

// Habilita las anotaciones de roles a rutas @PreAuthorize
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class GlobalSecurityConfig extends GlobalMethodSecurityConfiguration {

	// TODO: Alterar el comportamiento nativo del GlobalMethodSecurityConfiguration
	
}
