package com.example.th.service;

import java.util.Optional;

//import org.springframework.stereotype.Service;

import com.example.th.model.Product;

//@Service
public interface ProductService {

	public Iterable<Product> getAllProducts();
	
	public Product createProduct(String name, double price, String picture);  
	
	public Optional<Product> getProductById(long productId);
	
	public Product updateProductName(long productId, String name);
	
	public Product updateProductPrice(long productId, double price);
	
	public Product updateProductPicture(long productId, String picture);
	
	public Product removeProductById(long productId);
	
}
