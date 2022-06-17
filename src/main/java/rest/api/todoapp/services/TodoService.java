package rest.api.todoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.api.todoapp.exceptions.NoElementsException;
import rest.api.todoapp.exceptions.NoSuchTodoException;
import rest.api.todoapp.exceptions.NotAllParametersInUrlException;
import rest.api.todoapp.model.dao.repositories.TodoRepository;
import rest.api.todoapp.model.entities.Todo;
import rest.api.todoapp.services.dto.request.PaginationRequestTodoDTO;
import rest.api.todoapp.services.dto.request.TodoRequestDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
            throw new NoElementsException("List of todos is empty!");
        }
        return todos;
    }

    public UUID deleteTodoById(UUID todoId){
        return repository.deleteTodo(todoId);
    }

    public Todo updateTodoById(UUID todoId, TodoRequestDTO dto) {
        if( !todoId.equals(dto.getTodoId()) ){
            throw new NoSuchTodoException("todoId in path variable is not such as in request body");
        }
        Todo todo = new Todo(todoId, dto.getTitle(), dto.getBody(), LocalDateTime.now(), LocalDateTime.now());
        return repository.updateTodo(todo);
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


    public Todo getTodoById(UUID todoId) {
        List<Todo> list = getAllTodos();
        return list.stream()
                .filter( todo -> todo.getTodoId().equals(todoId) )
                .findAny()
                .orElseThrow( () -> new NoSuchTodoException("Error! There is now todo with such todoId!"));
    }
}
