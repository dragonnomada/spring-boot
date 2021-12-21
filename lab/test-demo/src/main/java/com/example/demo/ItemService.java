package com.example.demo;

import java.util.Optional;

public interface ItemService {

	public Optional<Item> findItemByName(String name);
	
	public Optional<Item> findItemById(long id);
	
	public Item addItem(String name);
	
}
