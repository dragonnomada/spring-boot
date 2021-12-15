package com.example.delivery.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.delivery.model.Store;

public interface StoreRepository extends CrudRepository<Store, Long> {
	
	//@Query("select s from Store s where s.name = ?1")
	Optional<Store> findByName(String name);

}
