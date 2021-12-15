package com.example.delivery.service;

import java.util.List;

import com.example.delivery.model.Package;
import com.example.delivery.model.Store;

public interface DeliveryService {
	
	public Store createStore(String name);
	
	public Package createPackageInStore(Long storeId, String guid);
	
	public Iterable<Store> getAllStore();
	
	public List<Package> getAllPackageInStore(Long storeId);

}
