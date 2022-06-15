package rest.api.todoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.api.todoapp.exceptions.NoElementsException;
import rest.api.todoapp.model.dao.repositories.TodoRepository;
import rest.api.todoapp.model.entities.Todo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public List<Todo> getAllTodos() throws NoElementsException {
        List<Todo> todos = repository.getAllTodos();
        if( todos.isEmpty() ){
            throw new NoElementsException("List of todos is empty!");
        }
        return repository.getAllTodos();
    }

    public long deleteTodoById(long todoId){
        return repository.deleteTodo(todoId);
    }

    public long updateTodoById(long todoId, Optional<String> title, Optional<String> body) {
        return repository.updateTodo(todoId, title, body);
    }

    public long saveTodoItem(String title, String body){
        return repository.saveTodo(title, body);
    }

}
