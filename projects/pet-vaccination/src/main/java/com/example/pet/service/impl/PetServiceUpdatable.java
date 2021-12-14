package com.example.pet.service.impl;

import com.example.pet.model.Pet;

public class PetServiceUpdatable extends PetServiceNoUpdatable {

	@Override
	public Pet updatePetName(long id, String name) {
		Pet pet = getById(id);
		
		if (pet != null) {
			pet.setName(name);
			
			return pet;
		}
		
		return null;
	}

	@Override
	public Pet setPetVaccinated(long id) {
		Pet pet = getById(id);
		
		if (pet != null) {
			pet.setVaccinated(true);
			
			return pet;
		}
		
		return null;
	}

	@Override
	public Pet setPetUnvaccinated(long id) {
		Pet pet = getById(id);
		
		if (pet != null) {
			pet.setVaccinated(false);
			
			return pet;
		}
		
		return null;
	}

}
