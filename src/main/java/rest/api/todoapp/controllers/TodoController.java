package rest.api.todoapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.api.todoapp.dto.request.PaginationRequestTodoDTO;
import rest.api.todoapp.dto.request.SaveTodoRequestDTO;
import rest.api.todoapp.dto.request.UpdateTodoRequestDTO;
import rest.api.todoapp.services.TodoService;
import rest.api.todoapp.entities.Todo;

import javax.validation.Valid;
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
    public Page<Todo> getPaginatedTodoList(@Valid PaginationRequestTodoDTO paginationDTO){
        return service.getPaginatedTodoList(paginationDTO.getPage(), paginationDTO.getPageSize());
    }

    @PostMapping("/")
    @Operation(summary = SAVE_TODO_ITEM_SUMMARY, description = SAVE_TODO_ITEM_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_STATUS_CREATED, description = "Todo successfully saved")
    public ResponseEntity<Todo> saveTodoItem(@Valid @RequestBody SaveTodoRequestDTO saveTodoRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.saveTodoItem(saveTodoRequestDTO.getTitle(), saveTodoRequestDTO.getBody()));
    }

    @PutMapping("/{todoId}")
    @Operation(summary = UPDATE_TODO_BY_ID_SUMMARY, description = UPDATE_TODO_BY_ID_DESCRIPTION)
    @ApiResponses({
            @ApiResponse(responseCode = HTTP_STATUS_OK, description = "Todo successfully updated"),
            @ApiResponse(responseCode = HTTP_STATUS_CREATED, description = "Todo successfully created")
    })
    public ResponseEntity<Todo> updateTodoById(@PathVariable UUID todoId, @Valid @RequestBody UpdateTodoRequestDTO dto){
        Todo todo = service.updateTodoById(todoId, dto);
        if(todo == null){
            SaveTodoRequestDTO saveTodoRequestDTO = new SaveTodoRequestDTO();
            saveTodoRequestDTO.setTitle(dto.getTitle());
            saveTodoRequestDTO.setBody(dto.getBody());
            return saveTodoItem(saveTodoRequestDTO);
        }
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = DELETE_TODO_BY_ID_SUMMARY, description = DELETE_TODO_BY_ID_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_STATUS_NO_CONTENT, description = "Todo successfully deleted")
    public void deleteTodoById(@PathVariable UUID todoId){
        service.deleteTodoById(todoId);
    }

}
