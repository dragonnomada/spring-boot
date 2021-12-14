package com.example.pet.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pet.model.Pet;
import com.example.pet.service.PetService;

@RestController
@RequestMapping("/api/pets")  
public class PetApiController {

	@Autowired
	@Qualifier("petServiceUpdatable") // @Qualifier("petServiceNoUpdatable")
	PetService petService;
	
	@GetMapping("")
	public List<Pet> getAll(@RequestParam(value="kind", defaultValue="*") String kind) {
		if (kind.equals("*")) {
			return petService.getAll();
		}
		
		return petService.getAllByKind(kind);		
	}
	
	@GetMapping("/{id}/vaccinated")
	public Pet updateVaccinated(@PathVariable("id") long id) {
		return petService.setPetVaccinated(id);
	}
	
}
