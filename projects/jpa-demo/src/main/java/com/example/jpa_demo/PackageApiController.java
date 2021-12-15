package com.example.jpa_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/packages")
public class PackageApiController {

	@Autowired
	PackageRepository packageRepository;
	
	@GetMapping(value = {"", "/"})
	public Iterable<Package> getAll() {
		return packageRepository.findAll();
	}
	
	@PutMapping("/new")
	public Package createNew(@RequestParam String guid) {
		// Package myPackage = new Package();
		// myPackage.setGuid(guid);
		// packageRepository.save(myPackage);
		return packageRepository.save(Package.builder().guid(guid).build());
	}
	
}
