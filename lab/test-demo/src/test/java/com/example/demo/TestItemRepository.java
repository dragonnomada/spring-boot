package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class TestItemRepository {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Test
	public void whenFindByName_thenReturnItem() {
		// given
	    Item item1 = Item.builder().name("Test 1").build();
	    entityManager.persist(item1);
	    entityManager.flush();

	    // when
	    Optional<Item> itemFoundOptional = itemRepository.findByName(item1.getName());

	    assertTrue(itemFoundOptional.isPresent());
	    
	    Item itemFound = itemFoundOptional.get();
	    
	    // then
	    assertThat(itemFound.getName())
	      .isEqualTo(item1.getName());
	}
	
}
