package rest.api.todoapp.model.dao.repositories;

import rest.api.todoapp.model.entities.Todo;

import java.util.List;
import java.util.UUID;

public interface TodoRepository {

    List<Todo> getAllTodos();

    Todo updateTodo(Todo todo);

    UUID deleteTodo(UUID todoId);

    UUID saveTodo(String title, String body);


}
