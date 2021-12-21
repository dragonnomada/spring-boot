package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public Optional<Item> findItemByName(String name) {
		return itemRepository.findByName(name);
	}

	@Override
	public Optional<Item> findItemById(long id) {
		return itemRepository.findById(id);
	}
	
	@Override
	public Item addItem(String name) {
		Item item = Item.builder().name(name).build();
		return itemRepository.save(item);
	}

}
