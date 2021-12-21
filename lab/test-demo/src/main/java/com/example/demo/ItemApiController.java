package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class ItemApiController {

	@Autowired
	private ItemService itemService;
	
	@PutMapping("/new")
	public Item newItem(@RequestParam String name) {
		return itemService.addItem(name);
	}
	
	@GetMapping("/{id}")
	public Optional<Item> getItem(@PathVariable long id) {
		return itemService.findItemById(id);
	}
	
}
