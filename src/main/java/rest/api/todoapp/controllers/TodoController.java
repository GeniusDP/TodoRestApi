package rest.api.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.api.todoapp.exceptions.NoElementsException;
import rest.api.todoapp.services.dto.request.PaginationRequestTodoDTO;
import rest.api.todoapp.services.dto.request.TodoRequestDTO;
import rest.api.todoapp.services.TodoService;
import rest.api.todoapp.model.entities.Todo;

import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos() throws NoElementsException {
        return ResponseEntity.ok( service.getAllTodos() );
    }

    @GetMapping("/todos/paginated")
    public ResponseEntity<List<Todo>> getPaginatedTodoList(PaginationRequestTodoDTO paginationDTO){
        return ResponseEntity.ok( service.getPaginatedTodoList(paginationDTO) );
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<String> updateTodoById(@PathVariable long todoId,
                                                 @RequestParam Optional<String> title,
                                                 @RequestParam Optional<String> body){
        String message = String.format("todo with id = %s successfully updates", service.updateTodoById(todoId, title, body));
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<String> deleteTodoById(@PathVariable long todoId){
        String message = String.format( "todo with id = %s successfully deleted", service.deleteTodoById(todoId) );
        return ResponseEntity.ok( message );
    }

    @PostMapping("/todos")
    public ResponseEntity<Long> saveTodoItem(@RequestBody TodoRequestDTO todoRequestDTO){
        long todoId = service.saveTodoItem(todoRequestDTO.getTitle(), todoRequestDTO.getBody());
        return ResponseEntity.ok(todoId);
    }

}
