package rest.api.todoapp.controllers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.api.todoapp.model.dao.repositories.TodoRepository;
import rest.api.todoapp.model.entities.Todo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public List<Todo> getAllTodos(){
        return repository.getAllTodos().orElse( Collections.emptyList() );
    }

    public int deleteTodoById(long todoId){
        return repository.deleteTodo(todoId);
    }

    public int updateTodoById(long todoId, Optional<String> title, Optional<String> body) {
        return repository.updateTodo(todoId, title, body);
    }
}
