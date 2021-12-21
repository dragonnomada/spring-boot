package com.example.server;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerTestRepositoryApplicationTests {

	@Test
	void test1_PrintMessage_ThenOk() {
		
		int x = 11;
		
		System.out.printf("x = %d\n", x);
		
		assertTrue(x > 10, "x tiene que ser mayor a 10");
		
		assertThat(x).withFailMessage("x tiene que ser 11").isEqualTo(11);
		
	}
	
	@Test
	void test2_Inspeccionar_Usuario_Defecto() {
		
		User user = User.defaultUser();
		
		System.out.println(user);
		
		assertThat(user)
			.withFailMessage("No tiene la propiedad id")
			.hasFieldOrProperty("id");
		assertThat(user)
			.withFailMessage("No tiene la propiedad name")
			.hasFieldOrProperty("name");
		assertThat(user)
		.withFailMessage("No tiene la propiedad age")
			.hasFieldOrProperty("age");
		
		assertThat(user.getId())
			.withFailMessage("El id no es null")
			.isEqualTo(null);
		assertThat(user.getName())
			.withFailMessage("El nombre Unknown")
			.isEqualTo("Unknown");
		assertThat(user.getAge())
			.withFailMessage("La edad no es 18")
			.isEqualTo(18);
		
		assertThat(user.toString())
			.withFailMessage("El formato que describe al usuario no es válido")
			.isEqualTo("[null] Unknown (18)");
		
	}

}
