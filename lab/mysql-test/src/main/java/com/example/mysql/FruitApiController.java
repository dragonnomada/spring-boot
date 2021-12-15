package com.example.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fruits")
public class FruitApiController {

	@Autowired
	FruitRepository fruitRepository;
	
	@Autowired
	FruitInfoRepository fruitInfoRepository;
	
	@GetMapping("")
	public Iterable<Fruit> fruits() {
		return fruitRepository.findAll();
	}
	
	@PutMapping("/new")
	public Fruit newFruit(@RequestParam("name") String name, @RequestParam("picture") String picture) {
		Fruit fruit = Fruit.builder().name(name).build();
		
		Fruit fruitSaved = fruitRepository.save(fruit);
		
		FruitInfo fruitInfo = FruitInfo.builder().picture(picture).build();
		
		FruitInfo fruitInfoSaved = fruitInfoRepository.save(fruitInfo);
		
		fruitSaved.setFruitInfo(fruitInfoSaved);
		
		fruitInfoSaved.setFruit(fruitSaved);
		
		fruitInfoRepository.save(fruitInfoSaved);
		
		return fruitRepository.save(fruitSaved);
	}
	
}
