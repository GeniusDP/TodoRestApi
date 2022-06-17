package rest.api.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String updateTodoById(@PathVariable UUID todoId, @RequestParam Optional<String> title, @RequestParam Optional<String> body){
        return String.format("todo with id = %s successfully updates", service.updateTodoById(todoId, title, body));
    }

    @DeleteMapping("/{todoId}")
    public String deleteTodoById(@PathVariable UUID todoId){
        return String.format( "todo with id = %s successfully deleted", service.deleteTodoById(todoId) );
    }

}
