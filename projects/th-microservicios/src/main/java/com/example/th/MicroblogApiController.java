package com.example.th;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Los Controladores tipo API están diseñados para que sean consumidos
// por cualquier cliente. Sin importar que no tengan una vista.
// Por ejemplo, otras empresas consumiendo nuestros servicios
// con sus propias interfaces.
@RestController
@RequestMapping("/api/blog")
public class MicroblogApiController {

	@Autowired
	MicroblogService microblogService;
	
	@PutMapping("/posts/new")
	public Post createPost(@RequestParam String content, @RequestParam String authorName) {		
		return microblogService.addPost(content, authorName);
	}
	
}
