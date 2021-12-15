package com.example.delivery.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.delivery.model.Store;
import com.example.delivery.model.Package;
import com.example.delivery.service.DeliveryService;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryApiController {

	@Autowired
	DeliveryService deliveryService;
	
	@GetMapping("/stores")
	public Iterable<Store> getStores() {
		return deliveryService.getAllStore();
	}
	
	@GetMapping("/stores/{storeId:\\d+}/products")
	public List<Package> getStorePackages(@PathVariable Long storeId) {
		return deliveryService.getAllPackageInStore(storeId);
	}
	
	@PutMapping("/stores/new")
	public Store newStore(@RequestParam String name) {
		return deliveryService.createStore(name);
	}
	
	@PatchMapping("/stores/{storeId:\\d+}/products/add")
	public Package newStore(@PathVariable Long storeId, @RequestParam String guid) {
		return deliveryService.createPackageInStore(storeId, guid);
	}
	
}
