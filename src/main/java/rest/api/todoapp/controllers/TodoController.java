package rest.api.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.api.todoapp.services.dto.request.PaginationRequestTodoDTO;
import rest.api.todoapp.services.dto.request.TodoRequestDTO;
import rest.api.todoapp.services.TodoService;
import rest.api.todoapp.model.entities.Todo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return service.getAllTodos();
    }

    @GetMapping("/{todoId}")
    public Todo getTodoById(@PathVariable UUID todoId){
        return service.getTodoById(todoId);
    }

    @GetMapping("/paginated")
    public List<Todo> getPaginatedTodoList(PaginationRequestTodoDTO paginationDTO){
        return service.getPaginatedTodoList(paginationDTO);
    }

    @PostMapping
    public UUID saveTodoItem(@RequestBody TodoRequestDTO todoRequestDTO){
        return service.saveTodoItem(todoRequestDTO.getTitle(), todoRequestDTO.getBody());
    }

    @PutMapping("/{todoId}")
    public Todo updateTodoById(@PathVariable UUID todoId, TodoRequestDTO dto){
        return service.updateTodoById(todoId, dto);
    }

    @DeleteMapping("/{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteTodoById(@PathVariable UUID todoId){
        return String.format( "todo with id = %s successfully deleted", service.deleteTodoById(todoId) );
    }

}
