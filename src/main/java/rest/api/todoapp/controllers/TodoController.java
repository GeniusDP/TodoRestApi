package rest.api.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rest.api.todoapp.controllers.services.TodoService;
import rest.api.todoapp.model.entities.Todo;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos(){
        return ResponseEntity.ok( service.getAllTodos() );
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<String> deleteTodoById(@PathVariable long todoId){
        int rowsEffected = service.deleteTodoById(todoId);
        int statusCode = rowsEffected == 0 ? 204 : 200;
        return ResponseEntity.status(statusCode).body("");
    }

}
