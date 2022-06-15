package rest.api.todoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rest.api.todoapp.exceptions.NoElementsException;
import rest.api.todoapp.exceptions.NotAllParametersInUrlException;
import rest.api.todoapp.model.dao.repositories.TodoRepository;
import rest.api.todoapp.model.entities.Todo;
import rest.api.todoapp.services.dto.request.PaginationRequestTodoDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public List<Todo> getAllTodos() {
        List<Todo> todos = repository.getAllTodos();
        if( todos.isEmpty() ){
            throw new NoElementsException("List of todos is empty!");
        }
        return todos;
    }

    public UUID deleteTodoById(UUID todoId){
        return repository.deleteTodo(todoId);
    }

    public UUID updateTodoById(UUID todoId, Optional<String> title, Optional<String> body) {
        return repository.updateTodo(todoId, title, body);
    }

    public UUID saveTodoItem(String title, String body){
        return repository.saveTodo(title, body);
    }

    public List<Todo> getPaginatedTodoList(PaginationRequestTodoDTO paginationDTO) {
        long page;
        long pageSize;
        try{
            page = paginationDTO.getPage();
            pageSize = paginationDTO.getPageSize();
        }catch (NullPointerException e){
            throw new NotAllParametersInUrlException("Error! Pagination require page > 0 and pageSize > 0 parameters!");
        }
        return getPaginatedTodoList(page, pageSize);
    }

    public List<Todo> getPaginatedTodoList(Long page, Long pageSize) {
        List<Todo> todos = getAllTodos().stream().skip((page - 1) * pageSize ).limit( pageSize ).collect(Collectors.toList());
        if( todos.isEmpty() ){
            throw new NoElementsException("List of todos is empty!");
        }
        return todos;
    }


}
