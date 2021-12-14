package com.example.pet.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.pet.model.Pet;
import com.example.pet.service.PetService;

public class PetServiceNoUpdatable implements PetService {

	protected ArrayList<Pet> pets = new ArrayList<>();
	
	@Override
	public List<Pet> getAll() {
		return pets.subList(0, pets.size());
	}

	@Override
	public List<Pet> getAllByKind(String kind) {
		ArrayList<Pet> petsByKind = new ArrayList<>();
		
		for (Pet pet : pets) {
			if (pet.getKind().toLowerCase().equals(kind.toLowerCase())) {
				petsByKind.add(pet);
			}
		}
		
		return petsByKind;
	}

	@Override
	public Pet getById(long id) {
		for (Pet pet : pets) {
			if (pet.getId() == id) {
				return pet;
			}
		}
		return null;
	}

	@Override
	public Pet addPet(String name, String kind) {
		long id = pets.size() + 1;
		
		Pet pet = Pet.builder()
				.id(id)
				.name(name)
				.kind(kind)
				.build();
		
		pets.add(pet);
		
		return getById(id);
	}

	@Override
	public Pet updatePetName(long id, String name) {
		return null;
	}

	@Override
	public Pet setPetVaccinated(long id) {
		return null;
	}

	@Override
	public Pet setPetUnvaccinated(long id) {
		return null;
	}

}
