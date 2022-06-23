package rest.api.todoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.api.todoapp.dto.request.UpdateTodoRequestDTO;
import rest.api.todoapp.entities.Todo;
import rest.api.todoapp.exceptions.NoSuchTodoException;
import rest.api.todoapp.dao.repositories.TodoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class TodoService {
    private final TodoRepository repository;

    @Autowired
    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAllTodos() {
        List<Todo> todos = repository.getAllTodos();
        if( todos.isEmpty() ){
            throw new NoSuchTodoException("List of todos is empty!");
        }
        return todos;
    }

    public Todo deleteTodoById(UUID todoId){
        return repository.deleteTodo(todoId);
    }

    public Todo updateTodoById(UUID todoId, UpdateTodoRequestDTO dto) {
        if( !todoId.equals( dto.getTodoId() ) ){
            throw new NoSuchTodoException("todoId in path variable is not such as in request body");
        }
        Todo todo = new Todo(todoId, dto.getTitle(), dto.getBody(), dto.getDone(), LocalDateTime.now(), LocalDateTime.now());
        return repository.updateTodo(todo);
    }

    public Todo saveTodoItem(String title, String body){
        return repository.saveTodo(title, body);
    }

    public List<Todo> getPaginatedTodoList(Long page, Long pageSize) {
        List<Todo> todos = repository.getPaginatedTodoList(page, pageSize);
        if( todos.isEmpty() ){
            throw new NoSuchTodoException("List of todos is empty!");
        }
        return todos;
    }


    public Todo getTodoById(UUID todoId) {
        Todo todoById = repository.getTodoById(todoId);
        if( Objects.isNull(todoById) ){
            throw new NoSuchTodoException("there is no such todo");
        }
        return todoById;
    }
}
