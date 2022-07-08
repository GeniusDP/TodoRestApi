package rest.api.todoapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import static rest.api.todoapp.Constants.TodoController.*;
import static rest.api.todoapp.Constants.Informer.*;

@RestController
@CrossOrigin
@RequestMapping("/todos")
@Tag(name = TAG, description = TAG_DESCRIPTION)
public class TodoController {

    private final TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/{todoId}")
    @Operation(summary = GET_TODO_BY_ID_SUMMARY, description = GET_TODO_BY_ID_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_STATUS_OK, description = "Todo successfully retrieved")
    public Todo getTodoById(@PathVariable UUID todoId){
        return service.getTodoById(todoId);
    }

    @GetMapping("/paginated")
    @Operation(summary = GET_PAGINATED_TODO_LIST_SUMMARY, description = GET_PAGINATED_TODO_LIST_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_STATUS_OK, description = "Page of todos successfully retrieved")
    public List<Todo> getPaginatedTodoList(@Valid PaginationRequestTodoDTO paginationDTO){
        return service.getPaginatedTodoList(paginationDTO.getPage(), paginationDTO.getPageSize());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = SAVE_TODO_ITEM_SUMMARY, description = SAVE_TODO_ITEM_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_STATUS_OK, description = "Todo successfully saved")
    public Todo saveTodoItem(@Valid @RequestBody SaveTodoRequestDTO saveTodoRequestDTO){
        return service.saveTodoItem(saveTodoRequestDTO.getTitle(), saveTodoRequestDTO.getBody());
    }

    @PutMapping("/{todoId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = UPDATE_TODO_BY_ID_SUMMARY, description = UPDATE_TODO_BY_ID_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_STATUS_OK, description = "Todo successfully updated or saved")
    public Todo updateTodoById(@PathVariable UUID todoId, @Valid @RequestBody UpdateTodoRequestDTO dto){
        return service.updateTodoById(todoId, dto);
    }

    @DeleteMapping("/{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = DELETE_TODO_BY_ID_SUMMARY, description = DELETE_TODO_BY_ID_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_STATUS_NO_CONTENT, description = "Todo successfully deleted")
    public void deleteTodoById(@PathVariable UUID todoId){
        service.deleteTodoById(todoId);
    }

}
