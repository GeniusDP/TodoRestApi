package rest.api.todoapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.api.todoapp.model.entities.Todo;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TodoController {

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos(){
        Todo todo = new Todo(
                "First todo in the app",
                "Super body for todo",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        return ResponseEntity.ok( List.of(todo) );
    }

}
