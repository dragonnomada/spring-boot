package com.example.secondapp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.secondapp.model.Todo;
import com.example.secondapp.service.TodoService;

public class TodoServiceImpl implements TodoService {

    ArrayList<Todo> todos = new ArrayList<>();

    @Override
    public Todo addTodo(String title) {
        Todo todo = new Todo(todos.size() + 1, title);
        todos.add(todo);
        return todo;
    }

    @Override
    public List<Todo> getTodos() {
        return todos.subList(0, todos.size());
    }

    private Todo getTodoById(int todoId) {
        for (Todo todo : todos) {
            if (todo.getId() == todoId) {
                return todo;
            }
        }

        return null;
    }

    @Override
    public Todo updateTodo(int todoId, boolean checked) {
        Todo todo = getTodoById(todoId);

        if (todo != null) {
            Todo updatedTodo = new Todo(
                todo.getId(), 
                todo.getTitle(), 
                checked, 
                todo.getCreateAt(), 
                new Date()
            );

            int index = todos.indexOf(todo);
            todos.set(index, updatedTodo);

            return updatedTodo;
        }

        return null;
    }

    @Override
    public Todo deleteTodo(int todoId) {
        Todo todo = getTodoById(todoId);

        if (todo != null) {
            int index = todos.indexOf(todo);
            todos.remove(index);

            return todo;
        }

        return null;
    }
    
}
