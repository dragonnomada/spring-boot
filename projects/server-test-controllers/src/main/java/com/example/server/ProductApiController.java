package com.example.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {

	private List<Product> products = new ArrayList<Product>();
	
	public ProductApiController() {
		super();
		
		products.add(Product.builder().id(1l).sku("abc123").name("Coca Cola").price(18.5).build());
		products.add(Product.builder().id(2l).sku("abc456").name("Galletas Marías").price(11.99).build());
		products.add(Product.builder().id(3l).sku("abc789").name("Gansito").price(10.23).build());
	}
	
	@GetMapping("")
	public List<Product> getAll() {
		return products;
	}
	
	@PutMapping("/{sku}/new")
	public Product addProduct(@PathVariable String sku, @RequestParam String name, @RequestParam double price) {
		Product product = Product.builder().id((long)products.size() + 1).sku(sku).name(name).price(price).build();
		
		products.add(product);
		
		return product;
	}
	
	@GetMapping("/{sku}")
	public Product getProduct(@PathVariable String sku) {
		for (Product product : products) {
			if (product.getSku().equals(sku)) return product;
		}
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid SKU of Product");
	}
	
}
