package com.example.secondapp.service;

import java.util.List;

import com.example.secondapp.model.Todo;

public interface TodoService {
    
    public Todo addTodo(String title);

    public List<Todo> getTodos();

    // public List<Todo> getTodos(boolean checked);

    public Todo updateTodo(int todoId, boolean checked);

    // public Todo updateTodo(Todo todo);

    public Todo deleteTodo(int todoId);

    // public Todo deleteTodo(Todo todo);

}
