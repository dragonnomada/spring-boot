package com.example.mysql;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores")
public class ProductApiController {

	@Autowired
	ProductStoreRepository productStoreRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductInfoRepository productInfoRepository;
	
	@GetMapping("")
	public Iterable<ProductStore> getStores() {
		return productStoreRepository.findAll();
	}
	
	
	@PutMapping("/new")
	public ProductStore newStore(@RequestParam("name") String name) {
		ProductStore store = new ProductStore();
		
		store.setName(name);
		
		return productStoreRepository.save(store);
	}
	
	@GetMapping("/{storeId}")
	public ProductStore getStore(@PathVariable("storeId") int storeId) {
		Optional<ProductStore> storeOptional = productStoreRepository.findById(storeId);
		
		if (storeOptional.isPresent()) {
			ProductStore store = storeOptional.get();
			
			return store;
		}
		
		return null;
	}
	
	@GetMapping("/{storeId}/products")
	public List<Product> getStoreProducts(@PathVariable("storeId") int storeId) {
		Optional<ProductStore> storeOptional = productStoreRepository.findById(storeId);
		
		if (storeOptional.isPresent()) {
			ProductStore store = storeOptional.get();
			
			return store.getProducts();
		}
		
		return null;
	}
	
	@PatchMapping("/{storeId}/products/add")
	public Product addProductToStore(@PathVariable("storeId") int storeId, @RequestParam("sku") String sku, @RequestParam("name") String name) {
		Optional<ProductStore> storeOptional = productStoreRepository.findById(storeId);
		
		if (storeOptional.isPresent()) {
			ProductStore store = storeOptional.get();
			
			Product p = new Product();
			p.setSku(sku);
			p.setProductStore(store);
			
			Product product = productRepository.save(p);
			
			ProductInfo pI = new ProductInfo();
			pI.setName(name);
			pI.setProduct(product);
			
			ProductInfo productInfo = productInfoRepository.save(pI);
			
			product.setProductInfo(productInfo);
			
			productRepository.save(product);
			
			/*Set<Product> products = store.getProducts();
			
			if (products == null) {
				products = new HashSet<Product>();
			}
			
			products.add(product);
			
			Set<Product> newProducts = new HashSet<Product>();
			
			newProducts.addAll(products);
			
			store.setProducts(newProducts);
			
			productStoreRepository.save(store);*/
			
			return product;
		}
		
		return null;
	}
	
}
