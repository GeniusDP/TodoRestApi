package rest.api.todoapp.model.dao.repositories;

import rest.api.todoapp.model.entities.Todo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoRepository {

    List<Todo> getAllTodos();

    UUID updateTodo(UUID todoId, Optional<String> title, Optional<String> body);

    UUID deleteTodo(UUID todoId);

    UUID saveTodo(String title, String body);


}
