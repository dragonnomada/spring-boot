package com.example.server;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class TestUserService {

	@TestConfiguration
	static class TestUserServiceConfig {
		
		@Bean
		public UserService userService() {
			// TODO: Confiurar un servicio de pruebas
			System.out.println("Bean UserService userSevice [TEST]");
			return new UserServiceImpl();
		}
		
	}
	
	@Autowired
	private UserService userService;
	
	@Test
	public void test1() {
		System.out.println("BEGIN test1SaveUsers");
		
		User user1 = userService.saveUser("Ana", 23);
		User user2 = userService.saveUser("Paco", 17);
		User user3 = userService.saveUser("Paty", 25);
		User user4 = userService.saveUser("Pepe", -1);
		
		assertNotNull(user1, "El usuario 1:Ana es nulo");
		assertNotNull(user2, "El usuario 2:Paco es nulo");
		assertNotNull(user3, "El usuario 3:Paty es nulo");
		assertNull(user4, "El usuario 4:Pepe se pudo guardar");
		
		assertThat(user1.getId())
			.withFailMessage("El usuario no tiene id 1")
			.isEqualTo(1);
		
		assertThat(user1.getName())
			.withFailMessage("El usuario es Ana")
			.isEqualTo("Ana");
		
		assertThat(user2.getId())
			.withFailMessage("El usuario no tiene id 2")
			.isEqualTo(2);
		
		assertThat(user2.getName())
			.withFailMessage("El usuario es Paco")
			.isEqualTo("Paco");
		
		assertThat(user3.getId())
			.withFailMessage("El usuario no tiene id 3")
			.isEqualTo(3);
		
		assertThat(user3.getName())
			.withFailMessage("El usuario es Paty")
			.isEqualTo("Paty");
		
		System.out.println("END test1SaveUsers");
	}
	
	@Test
	public void test2() {
		System.out.println("BEGIN test2VerifyUsers");
		
		Iterable<User> users = userService.getAllUsers();
		
		long id = 1;
		
		for (User user : users) {
			assertThat(user.getId())
				.withFailMessage(String.format("El usuario %s no tiene el id %d", user, id))
				.isEqualTo(id++);
		}
		
		assertTrue(id == 4, String.format("Hay más usuarios de los esperados: Último id=%d", id));
		
		System.out.println("END test2VerifyUsers");
	}
	
	@Test
	public void test3() {
		System.out.println("BEGIN test3FindPaco");
		
		Optional<User> userOptional = userService.findUserByName("Paco");
		
		assertTrue(userOptional.isPresent(), "No se encontró a Paco");
		
		User user = userOptional.get();
		
		System.out.println(user);
		
		assertThat(user.getAge())
			.withFailMessage("Paco no tiene 17 años")
			.isEqualTo(17);
		
		System.out.println("END test3FindPaco");
	}
	
	@Test
	public void test4() {
		System.out.println("BEGIN test4FindAge_15_24");
		
		List<User> users = userService.findByAgeBetween(15, 24);
		
		for (User user : users) {
			System.out.println(user);
		}
		
		assertThat(users.size())
			.withFailMessage("Hay más de 2 usuario con edad entre 15 y 24 años")
			.isEqualTo(2);
		
		System.out.println("END test4FindAge_15_24");
	}
	
	@Test
	public void test5() {
		System.out.println("BEGIN test5FindAge_24_15");
		
		List<User> users = userService.findByAgeBetween(24, 15);
		
		for (User user : users) {
			System.out.println(user);
		}
		
		assertThat(users.size())
			.withFailMessage("Hay más de 2 usuario con edad entre 25 y 15 años")
			.isEqualTo(2);
		
		System.out.println("BEGIN test5FindAge_24_15");
	}
}
