package com.example.server;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class TestUserRepository {

	@Autowired
	private TestEntityManager entityManager; // Simulated JPA EntityManager
	
	@Autowired
	private UserRepository userRepository; // Simulated UserRepository -> JPA EntityManager | Do not use JPA H2 DB
	
	@Test
	public void test1SaveUsers() {
		System.out.println("test1_saveUsers");
		
		User user = User.builder().name("Ana").age(23).build();
		
		User userSaved = entityManager.persistAndFlush(user);// entityManager.persistAndFlush(user); // userRepository.save(user);
		
		assertThat(userSaved.getId())
			.withFailMessage("El usuario no tiene el primer id")
			.isEqualTo(1);
		
		List<String> names = new ArrayList<String>();
		
		names.add("Pedro");
		names.add("Bety");
		names.add("Paco");
		names.add("Dany");
		names.add("Jessy");
		
		int id = 1;
		
		for (String name : names) {
			User theUser = User.builder().name(name).age(5 * id).build();
			
			User nextUser = entityManager.persistAndFlush(theUser); //entityManager.persistAndFlush(theUser); //userRepository.save(theUser);
			
			assertThat(nextUser.getId())
				.withFailMessage(String.format("El usuario no tiene id = %d <> %d", ++id, nextUser.getId()))
				.isEqualTo(id);
		}
		
		System.out.println("test2_checkIfFirstUser_isAna");
		
		int total = 0;
		
		for (User user_ : userRepository.findAll()) {
			System.out.println(user_);
			total++;
		}
		
		System.out.printf("Users: %d\n", total);
		
		Optional<User> userOptional = userRepository.findById(1l);
		
		assertTrue(userOptional.isPresent(), "No se encuentra el primer usuario");
		
		User userAna = userOptional.get();
		
		assertThat(userAna.getId())
			.withFailMessage("No tiene id 1")
			.isEqualTo(1l);
		
		assertThat(userAna.getName())
			.withFailMessage("No es Ana")
			.isEqualTo("Ana");
		
		assertThat(userAna.getAge())
			.withFailMessage("No tine 23 años")
			.isEqualTo(23);
	}
	
	
	
}
