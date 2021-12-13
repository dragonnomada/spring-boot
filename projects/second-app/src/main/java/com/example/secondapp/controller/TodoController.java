package com.example.secondapp.controller;

import java.util.List;

import com.example.secondapp.model.Todo;
import com.example.secondapp.service.TodoService;
import com.example.secondapp.service.impl.TodoServiceImpl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    TodoService todoService = new TodoServiceImpl();
    
    @PutMapping("/new")
    public Todo putTodo(@RequestParam("title") String title) {
        return todoService.addTodo(title);
    }
    
    @GetMapping("")
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }
    
    @PostMapping("/{todoId:\\d+}/update/checked")
    public Todo updateTodoChecked(@PathVariable("todoId") int todoId) {
        return todoService.updateTodo(todoId, true);
    }
    
    @PostMapping("/{todoId:\\d+}/update/unchecked")
    public Todo updateTodoUnchecked(@PathVariable("todoId") int todoId) {
        return todoService.updateTodo(todoId, false);
    }

}
