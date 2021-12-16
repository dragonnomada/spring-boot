package com.example.th.controller.view;

import java.util.Optional;

//import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.th.model.Product;
import com.example.th.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductViewController {

	@Autowired
	ProductService productService;
	
	/*
	 * model.addAttribute("products", productService.getAllProducts())
	 */
	@ModelAttribute("products")
	public Iterable<Product> products() {
		return productService.getAllProducts();
	}
	
	@GetMapping("")
	public String getProducts() {
		return "ProductsHome";
	}
	
	/*@RequestMapping("/new")
	public String createProductAll(HttpServletRequest request, BindingResult result, Model model) {
		if (request.getMethod() == "POST") {
			
		} else if (request.getMethod() == "GET") {
			
		}
	}*/
	
	@GetMapping("/new") // GET /products/new
	public String createProductGet(Model model) {
		model.addAttribute("product", new Product());
		
		return "ProductNew";
	}
	
	@PostMapping("/new") // POST /products/new
	public String createProductPost(@ModelAttribute Product product, Model model) {
		Product productCreated = productService.createProduct(product.getName(), product.getPrice(), product.getPicture());
		
		if (productCreated != null) {
			return "redirect:/products/" + productCreated.getId();
		}
		
		model.addAttribute("product", product);
		model.addAttribute("error", "No se pudo crear el producto");
		return "ProductNew";
	}
	
	@GetMapping("/{productId}")
	public String getProductInfo(@PathVariable long productId, Model model) {
		Optional<Product> productOptional = productService.getProductById(productId);
		
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			
			model.addAttribute("product", product);
			
			return "ProductInfo";
		}
		
		model.addAttribute("productId", productId);
		
		return "ProductNotFound";
	}
	
	@GetMapping("/{productId}/edit")
	public String editProductGet(@PathVariable long productId, Model model) {
		Optional<Product> productOptional = productService.getProductById(productId);
		
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			
			model.addAttribute("product", product);
			
			return "ProductEdit";
		}
		
		model.addAttribute("productId", productId);
		
		return "ProductNotFound";
	}
	
	@PostMapping("/{productId}/edit")
	public String editProductPost(@PathVariable long productId, @ModelAttribute Product product, Model model) {
		Optional<Product> productOptional = productService.getProductById(productId);
		
		if (productOptional.isPresent()) {
			productService.updateProductName(productId, product.getName());
			productService.updateProductPrice(productId, product.getPrice());
			productService.updateProductPicture(productId, product.getPicture());
			
			return "redirect:/products/" + productId;
		}
		
		model.addAttribute("productId", productId);
		
		return "ProductNotFound";
	}
	
	@GetMapping("/{productId}/remove")
	public String removeProduct(@PathVariable long productId, Model model) {
		Optional<Product> productOptional = productService.getProductById(productId);
		
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			
			model.addAttribute("product", product);
			
			return "ProductRemove";
		}
		
		model.addAttribute("productId", productId);
		
		return "ProductNotFound";
	}
	
	@PostMapping("/{productId}/remove")
	public String removeProductPost(@PathVariable long productId, Model model) {
		Optional<Product> productOptional = productService.getProductById(productId);
		
		if (productOptional.isPresent()) {
			Product product = productService.removeProductById(productId);
			
			model.addAttribute("product", product);
			
			return "redirect:/products";
		}
		
		model.addAttribute("productId", productId);
		
		return "ProductNotFound";
	}
	
}
