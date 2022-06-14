package rest.api.todoapp.model.dao.repositories;

import rest.api.todoapp.model.entities.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {

    Optional<List<Todo>> getAllTodos();

    void updateTodo(long todoId);

    void deleteTodo(long todoId);

    void saveTodo(Todo todo);
}
