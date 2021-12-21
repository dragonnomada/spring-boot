package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@AutoConfigureMockMvc
public class TestItemApiController {
	
	@TestConfiguration
	static public class TestItemServiceConfig {

		@Bean
	    public ItemService itemService() {
			System.out.println("Usando Test en TestItemApiController");
	    	return new ItemServiceImpl();
	    }
		
	}
	
	@Autowired
	@Qualifier("itemService")
    private ItemService itemService;
	
	@Autowired
    private MockMvc mvc;
	
	@Test
	public void test1_newItem_thenReturnItem() throws Exception {  
		System.out.println("newItem_thenReturnItem");
		
    	mvc.perform(put("/api/items/new")
    			.param("name", "Test A")
	    		.contentType(MediaType.APPLICATION_JSON))
	    		.andExpect(status().isOk())
	    		.andExpect(jsonPath("$.id").value(1))
	    		.andExpect(jsonPath("$.name").value("Test A"));
	}
	
	@Test
	public void test2_getItem_thenReturnItem() throws Exception {  
		System.out.println("getItem_thenReturnItem");
		
    	mvc.perform(get("/api/items/1")
	    		.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andDo(MockMvcResultHandlers.print())
	    		.andExpect(jsonPath("$.id").value(1))
	    		.andExpect(jsonPath("$.name").value("Test A"));
	}
}

// .header("Authorization", "Bearer XXXXX")
// .andExpect(xpath("//input[@name='summary']").exists())
// .andExpect(redirectedUrl("/messages/123"));
// .andExpect(content().string(html))
// .andExpect(content().string("Hello there!"));
// .andExpect(content().string(containsString("This content is only shown to users.")));

/*MvcResult mvcResult = mockMvc.
perform(post(apiToTest).
contentType(MediaType.APPLICATION_JSON)).
andReturn();

String responseData = mvcResult.getResponse().getContentAsString();*/
