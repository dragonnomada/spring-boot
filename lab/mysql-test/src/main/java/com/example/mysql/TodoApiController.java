package com.example.mysql;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
public class TodoApiController {

	@Autowired
	TodoRepository todoRepository;
	
	@GetMapping("")
	public Iterable<Todo> getAll() {
		return todoRepository.findAll();
	}
	
	@PutMapping("/new")
	public Todo newTodo(@RequestParam("title") String title) {
		
		Todo todo = Todo.builder().title(title).checked(false).build();
		
		todoRepository.save(todo);
		
		return todo;
		
	}
	
	@PostMapping("/{id}/check")
	public Todo checkTodo(@PathVariable("id") int id) {
		
		Optional<Todo> todoOptional = todoRepository.findById(id);
		
		if (todoOptional.isPresent()) {
			Todo todo = todoOptional.get();
			todo.setChecked(true);
			todoRepository.save(todo);
			return todo;
		}
		
		return null;
		
	}
	
}
