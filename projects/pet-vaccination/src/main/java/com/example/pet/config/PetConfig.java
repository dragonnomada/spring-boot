package com.example.pet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.pet.service.PetService;
import com.example.pet.service.impl.PetServiceNoUpdatable;
import com.example.pet.service.impl.PetServiceUpdatable;

@Configuration
public class PetConfig {

	// El bean es responsable de almacenar una instancia devuelta
	// por el método en cuestión, para que esta sea conectable
	// en otras partes del código a través de @Autowired.
	// La instancia debe ser de alguna clase o interfaz.
	// Pero generalmente son de interfaces, para no comprometer
	// código de implementación. Es decir, ser más genéricos.
	@Bean 
	public PetService petServiceNoUpdatable() {
		// Creamos la instancia que será devuelta como servicio genérico
		PetServiceNoUpdatable petServiceNoUpdatable = new PetServiceNoUpdatable();
		
		// Configurar el servicio inicial (consultas inicial, métricas)
		petServiceNoUpdatable.addPet("Bombón", "perro");
		petServiceNoUpdatable.addPet("Shakmol", "gato");
		petServiceNoUpdatable.addPet("Firulais", "perro");
		petServiceNoUpdatable.addPet("Flyi", "perico");
		
		return (PetService)petServiceNoUpdatable;
	}
	
	@Bean 
	public PetService petServiceUpdatable() {
		// Creamos la instancia que será devuelta como servicio genérico
		PetServiceUpdatable petServiceUpdatable = new PetServiceUpdatable();
		
		// Configurar el servicio inicial (consultas inicial, métricas)
		petServiceUpdatable.addPet("Pachico", "perro");
		petServiceUpdatable.addPet("Solovino", "perro");
		petServiceUpdatable.addPet("Coco", "perico");
		
		return (PetService)petServiceUpdatable;
	}
	
}
