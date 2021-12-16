package com.example.th;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonViewController {

	@GetMapping("/person/{personId}")
	public String viewPerson(Model model, @PathVariable Long personId) {
		Person person = Person.builder()
				.id(personId)
				.name(String.format("Persona %d", personId))
				.age((int)(personId * 10))
				.build();
		
		model.addAttribute("person", person);
		
		return "person";
	}
	
	@GetMapping("/person/{personId}/edit")
	public String editPerson(Model model, @PathVariable Long personId) {
		Person person = Person.builder()
				.id(personId)
				.name(String.format("Persona %d", personId))
				.age((int)(personId * 10))
				.build();
		
		model.addAttribute("person", person);
		
		return "person_edit";
	}
	
	@PostMapping("/person/{personId}/update")
	public String updatePerson(Model model, @PathVariable Long personId, @ModelAttribute Person person) {
		model.addAttribute("person_updated", person);
		
		System.out.println(person.getId());
		System.out.println(person.getName());
		System.out.println(person.getAge());
		
		return "redirect:/person/" + personId.toString();
	}
	
}
