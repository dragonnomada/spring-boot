package com.example.delivery.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.delivery.model.Package;
import com.example.delivery.model.Store;
import com.example.delivery.repository.PackageRepository;
import com.example.delivery.repository.StoreRepository;
import com.example.delivery.service.DeliveryService;

public final class DeliverServiceImpl implements DeliveryService {

	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	PackageRepository packageRepository;
	
	@Override
	public Store createStore(String name) {
		Optional<Store> storeOptional = storeRepository.findByName(name);
		
		if (storeOptional.isEmpty()) {
			return storeRepository.save(Store.builder().name(name).build());
		}
		
		// TODO: Producir un error advirtiendo el almacén con ese nombre ya existe
		return null;
	}

	@Override
	public Package createPackageInStore(Long storeId, String guid) {
		Optional<Store> storeOptional = storeRepository.findById(storeId);
		
		if (storeOptional.isPresent()) {
			Store store = storeOptional.get();
			
			Package pack = packageRepository.save(
					Package.builder().store(store).guid(guid).build());
			
			List<Package> packages = store.getPackages();
			
			packages.add(pack);
			
			store.setPackages(packages);
			
			storeRepository.save(store);
			
			return pack;
		}
		
		// TODO: Producir un error advirtiendo el almacén no existe
		return null;
	}

	@Override
	public Iterable<Store> getAllStore() {
		return storeRepository.findAll();
	}

	@Override
	public List<Package> getAllPackageInStore(Long storeId) {
		Optional<Store> storeOptional = storeRepository.findById(storeId);
		
		if (storeOptional.isPresent()) {
			Store store = storeOptional.get();
			
			List<Package> packages = store.getPackages();
			
			return packages;
		}
		
		// TODO: Producir un error advirtiendo el almacén no existe
		return null;
	}

}
