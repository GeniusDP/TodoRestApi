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
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok( service.getAllTodos() );
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodoById(@PathVariable UUID todoId){
        return ResponseEntity.ok( service.getTodoById(todoId) );
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<Todo>> getPaginatedTodoList(PaginationRequestTodoDTO paginationDTO){
        return ResponseEntity.ok( service.getPaginatedTodoList(paginationDTO) );
    }

    @PostMapping
    public ResponseEntity<UUID> saveTodoItem(@RequestBody TodoRequestDTO todoRequestDTO){
        UUID todoId = service.saveTodoItem(todoRequestDTO.getTitle(), todoRequestDTO.getBody());
        return ResponseEntity.ok(todoId);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<String> updateTodoById(@PathVariable UUID todoId, @RequestParam Optional<String> title, @RequestParam Optional<String> body){
        String message = String.format("todo with id = %s successfully updates", service.updateTodoById(todoId, title, body));
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoById(@PathVariable UUID todoId){
        String message = String.format( "todo with id = %s successfully deleted", service.deleteTodoById(todoId) );
        return ResponseEntity.ok( message );
    }

}
