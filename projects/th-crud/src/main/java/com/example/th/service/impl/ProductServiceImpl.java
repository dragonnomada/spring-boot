package com.example.th.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.th.model.Product;
import com.example.th.repository.ProductRepository;
import com.example.th.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product createProduct(String name, double price, String picture) {
		Product product = Product.builder()
				.name(name)
				.price(price)
				.picture(picture)
				.build();
		
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> getProductById(long productId) {
		return productRepository.findById(productId);
	}

	@Override
	public Product updateProductName(long productId, String name) {
		Optional<Product> productOptional = productRepository.findById(productId);
		
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			
			product.setName(name);
			
			return productRepository.save(product);
		}
		
		return null;
	}

	@Override
	public Product updateProductPrice(long productId, double price) {
		if (price <= 0) {
			return null;
		}
		
		Optional<Product> productOptional = productRepository.findById(productId);
		
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			
			product.setPrice(price);
			
			return productRepository.save(product);
		}
		
		return null;
	}

	@Override
	public Product updateProductPicture(long productId, String picture) {
		Optional<Product> productOptional = productRepository.findById(productId);
		
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			
			product.setPicture(picture);
			
			return productRepository.save(product);
		}
		
		return null;
	}

	@Override
	public Product removeProductById(long productId) {
		Optional<Product> productOptional = productRepository.findById(productId);
		
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			
			productRepository.deleteById(productId);
			
			return product;
		}
		
		return null;
	}

}
