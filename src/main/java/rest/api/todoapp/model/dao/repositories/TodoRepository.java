package rest.api.todoapp.model.dao.repositories;

import rest.api.todoapp.model.entities.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {

    List<Todo> getAllTodos();

    long updateTodo(long todoId, Optional<String> title, Optional<String> body);

    long deleteTodo(long todoId);

    long saveTodo(String title, String body);


}
