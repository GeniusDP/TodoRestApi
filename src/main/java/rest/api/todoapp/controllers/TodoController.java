package rest.api.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
