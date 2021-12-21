package com.example.server;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@AutoConfigureMockMvc
public class TestProductApiController {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void test1() throws Exception {
		System.out.println("Obtener la lista de productos");
		
		RequestBuilder request = get("/api/products");
		
		MvcResult result = mvc.perform(request).andReturn();
		
		assertThat(result.getResponse().getStatus())
			.withFailMessage("El estatus http no es 200")
			.isEqualTo(200);
		
		String resultJson = result.getResponse().getContentAsString();
		
		System.out.println(resultJson);
		
	}
	
	@Test
	public void test2() throws Exception {
		System.out.println("Obtener la lista de productos y verficar los datos");
		
		RequestBuilder request = get("/api/products");
		
		mvc.perform(request)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0]").isMap())
			.andExpect(jsonPath("$[0].id").isNumber())
			.andExpect(jsonPath("$[0].id").value(1))
			.andExpect(jsonPath("$[0].name").isString())
			.andExpect(jsonPath("$[0].name").value("Coca Cola"))
			.andReturn();
	}
	
	@Test
	public void test3() throws Exception {
		System.out.println("Crear un producto nuevo con sku XYZ123");
		
		RequestBuilder request = put("/api/products/XYZ123/new")
				.param("name", "My Test Product")
				.param("price", "99.99");
		
		mvc.perform(request)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isMap())
			.andExpect(jsonPath("$.id").isNumber())
			.andExpect(jsonPath("$.id").value(4))
			.andExpect(jsonPath("$.name").isString())
			.andExpect(jsonPath("$.name").value("My Test Product"))
			.andExpect(jsonPath("$.price").isNumber())
			.andExpect(jsonPath("$.price").value(99.99))
			.andReturn();
	}
	
	@Test
	public void test4() throws Exception {
		System.out.println("Consultar el producto con sku XYZ123");
		
		/*RequestBuilder request = ... // TODO: Buscar el producto XYZ123
		
		mvc.perform(request)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isMap())
			.andExpect(jsonPath("$.id").isNumber())
			.andExpect(jsonPath("$.id").value(4))
			.andExpect(jsonPath("$.name").isString())
			.andExpect(jsonPath("$.name").value("My Test Product"))
			.andExpect(jsonPath("$.price").isNumber())
			.andExpect(jsonPath("$.price").value(99.99))
			.andReturn();*/
	}
	
}
