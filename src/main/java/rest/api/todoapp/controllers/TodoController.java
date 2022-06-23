package rest.api.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rest.api.todoapp.dto.request.PaginationRequestTodoDTO;
import rest.api.todoapp.dto.request.SaveTodoRequestDTO;
import rest.api.todoapp.dto.request.UpdateTodoRequestDTO;
import rest.api.todoapp.services.TodoService;
import rest.api.todoapp.entities.Todo;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/todos")
public class TodoController {
    private final TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Todo> getAllTodos() {
        return service.getAllTodos();
    }

    @GetMapping("/{todoId}")
    public Todo getTodoById(@PathVariable UUID todoId){
        return service.getTodoById(todoId);
    }

    @GetMapping("/{todoId}/title")
    public String getTitleOfTodoById(@PathVariable UUID todoId){
        return service.getTodoById(todoId).getTitle();
    }

    @GetMapping("/{todoId}/body")
    public String getBodyOfTodoById(@PathVariable UUID todoId){
        return service.getTodoById(todoId).getBody();
    }


    @GetMapping("/paginated")
    public List<Todo> getPaginatedTodoList(@Valid PaginationRequestTodoDTO paginationDTO){
        return service.getPaginatedTodoList(paginationDTO.getPage(), paginationDTO.getPageSize());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo saveTodoItem(@Valid @RequestBody SaveTodoRequestDTO saveTodoRequestDTO){
        return service.saveTodoItem(saveTodoRequestDTO.getTitle(), saveTodoRequestDTO.getBody());
    }

    @PutMapping("/{todoId}")
    public Todo updateTodoById(@PathVariable UUID todoId, @Valid @RequestBody UpdateTodoRequestDTO dto){
        return service.updateTodoById(todoId, dto);
    }

    @DeleteMapping("/{todoId}")
    public Todo deleteTodoById(@PathVariable UUID todoId){
        return service.deleteTodoById(todoId);
    }

}
