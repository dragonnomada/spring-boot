package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
//@Import({TestItemServiceConfig.class})
public class TestItemService {

	@TestConfiguration
	static public class TestItemServiceConfig {

		@Bean
	    public ItemService itemService() {
			System.out.println("Usando Test en TestItemService");
	    	return new ItemServiceImpl();
	    }
		
	}
	
	@Autowired
	@Qualifier("itemService")
    private ItemService itemService;
	
	@Test
	public void whenValidName_thenItemShouldBeFound() {    	
    	List<String> itemNames = new ArrayList<String>();
    	
    	itemNames.add("Test 1");
    	itemNames.add("Test 2");
    	itemNames.add("Test 3");
    	
    	for (String name : itemNames) {
    		Item item = itemService.addItem(name);
    		
    		assertThat(item.getName())
    			.isEqualTo(name);
    	}
    	
    	//itemNames.add("Test 4");
    	
		for (String name : itemNames) {
			Optional<Item> itemFoundOptional = itemService.findItemByName(name);
			 
		    System.out.println("itemFoundOptional.isPresent");
		    System.out.println(itemFoundOptional.isPresent());
		    
		    assertTrue(itemFoundOptional.isPresent());
		    
		    Item itemFound = itemFoundOptional.get();
		    
		    System.out.println(itemFound.getId());
		    System.out.println(itemFound.getName());
		    
		     assertThat(itemFound.getName())
		     	.isEqualTo(name);
		}
	 }
	
}
