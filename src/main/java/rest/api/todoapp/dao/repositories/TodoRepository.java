package rest.api.todoapp.dao.repositories;

import rest.api.todoapp.entities.Todo;

import java.util.List;
import java.util.UUID;

public interface TodoRepository {

    List<Todo> getAllTodos();

    Todo updateTodo(Todo todo);

    Todo deleteTodo(UUID todoId);

    Todo saveTodo(String title, String body);

    Todo getTodoById(UUID todoId);

}
