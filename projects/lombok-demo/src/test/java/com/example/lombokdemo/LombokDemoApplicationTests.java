package com.example.lombokdemo;

import com.example.lombokdemo.model.Color;
import com.example.lombokdemo.model.Student;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LombokDemoApplicationTests {

	@Test
	void contextLoads() {

	}
	
	@Test
	void testColors() {

		Color color1 = new Color(1, "Rojo", 255, 0, 0);
		Color color2 = Color.builder().id(2).color("Azul").build();

		System.out.println(color1);
		System.out.println(color2);

	}
	
	@Test
	void testStudents() {

		Student studentA = new Student(1, "Pepe", "Gómez", false);
		
		studentA.setActive(true);
		
		Student studentB = Student.builder()
				.id(2)
				.name("Paco")
				.lastName("Juárez")
				.active(true)
				.build();

		System.out.println(studentA);
		System.out.println(studentB);

	}

}
