package com.example.pet.service;

import java.util.List;

import com.example.pet.model.Pet;

public interface PetService {
	
	public List<Pet> getAll();
	
	public List<Pet> getAllByKind(String kind);
	
	public Pet getById(long id);
	
	public Pet addPet(String name, String kind);
	
	public Pet updatePetName(long id, String name);
	
	public Pet setPetVaccinated(long id);
	
	public Pet setPetUnvaccinated(long id);
	
}
