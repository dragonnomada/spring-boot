package com.example.th;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

// Los Controladores de tipo VISTA están diseñados para que sean consumidos
// por nuestros propios usuarios, y guiar la forma en la que se consumen
// nuestros servicios. Es decir, proponer una forma visual de consumir
// nuestras API.
@Controller
@RequestMapping("/")
public class MicroblogViewController {
	
	@Autowired
	MicroblogService microbloService;
	
	@ModelAttribute("posts")
	public List<Post> getAllPosts() {
		return microbloService.getAllPosts();
	}
	
	@GetMapping("")
	public String home() {
		return "MicroblogHome";
	}
	
}
