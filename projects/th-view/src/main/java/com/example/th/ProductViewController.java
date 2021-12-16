package com.example.th;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductViewController {

	// Los controladores soportan atributos inyectados
	// los cuales al ser detectados pueden cambiar el controlador
	// Algunos Importantes son:
	// HttpServletRequest request - Controla la petición del controlador
	// HttpServletResponse response - Controla la respuesta del controlador
	// Model model - Controla el modelo asociado a los atributos del controlador
	
	@GetMapping("/product")
	public String productInfo(Model model) {
		model.addAttribute("title", "Hola mundo mundial");
		
		Product product = new Product(1, "Chetos bolita", 42.5);
		
		model.addAttribute("product", product);
		
		List<Product> products = new ArrayList<Product>();
		
		products.add(new Product(2, "Gansito", 8.5));
		products.add(new Product(3, "Nito", 38.5));
		products.add(new Product(4, "Chocorroll", 28.5));
		products.add(new Product(5, "Chocotorro", 18.5));
		
		model.addAttribute("products", products);
		
		return "productInfo";
	}
	
}
