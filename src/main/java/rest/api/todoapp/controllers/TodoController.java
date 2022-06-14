package rest.api.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.api.todoapp.controllers.dto.TodoUpdateParameters;
import rest.api.todoapp.controllers.services.TodoService;
import rest.api.todoapp.model.entities.Todo;

import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos(){
        return ResponseEntity.ok( service.getAllTodos() );
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<String> updateTodoById(@PathVariable long todoId, TodoUpdateParameters todoUpdateParameters){
        System.out.println(todoUpdateParameters.getTitle());
        System.out.println(todoUpdateParameters.getBody());
        int rowsEffected = service.updateTodoById(
                todoId,
                Optional.ofNullable(todoUpdateParameters.getTitle()),
                Optional.ofNullable(todoUpdateParameters.getBody())
        );
        int statusCode = rowsEffected == 0 ? 204 : 200;
        return ResponseEntity.status(statusCode).body(null);
    }


    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<String> deleteTodoById(@PathVariable long todoId){
        int rowsEffected = service.deleteTodoById(todoId);
        int statusCode = rowsEffected == 0 ? 204 : 200;
        return ResponseEntity.status(statusCode).body(null);
    }

}
