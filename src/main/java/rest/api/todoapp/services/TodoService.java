package rest.api.todoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rest.api.todoapp.dao.TodoRepository;
import rest.api.todoapp.dto.request.UpdateTodoRequestDTO;
import rest.api.todoapp.entities.Todo;
import rest.api.todoapp.exceptions.MissedRequiredArgumentsException;
import rest.api.todoapp.exceptions.NoSuchTodoException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.UUID;

@Service
public class TodoService {
    private final TodoRepository repository;

    @Autowired
    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public void deleteTodoById(UUID todoId){
        if( repository.findById(todoId).isPresent() ){
            repository.deleteById(todoId);
            return;
        }
        throw new NoSuchTodoException("there is no such todo");
    }

    public Todo updateTodoById(UUID todoId, UpdateTodoRequestDTO dto) {
        if( !dto.getTodoId().equals( todoId ) ){
            throw new NoSuchTodoException("todoId in path variable is not such as in request body, but should be");
        }
        Optional<Todo> todo = repository.findById(todoId);
        if( todo.isPresent() ){
            return repository.save(updateTodoEntity(todo.get(), dto));
        }
        return saveIfNotExistingEntityUpdated(todoId, dto.getTitle(), dto.getBody());
    }

    private Todo saveIfNotExistingEntityUpdated(UUID todoId, String title, String body) {
        if( title != null && body != null ){
            repository.saveWithProvidedId_Native(todoId, title, body, LocalDateTime.now());
            return this.getTodoById(todoId);
        }
        String message = new StringJoiner(", ", "missed arguments: {", "}")
                .add(title == null ? "title" : "")
                .add(body == null ? "body" : "")
                .toString();
        throw new MissedRequiredArgumentsException(message);
    }

    private Todo updateTodoEntity(Todo todo, UpdateTodoRequestDTO dto) {
        if( dto.getDone() != null ){
            todo.setDone(dto.getDone());
        }
        if( dto.getTitle() != null ){
            todo.setTitle(dto.getTitle());
        }
        if( dto.getBody() != null ){
            todo.setBody(dto.getBody());
        }
        return todo;
    }

    public Todo saveTodoItem(String title, String body){
        Todo todo = new Todo(title, body, false);
        return repository.save(todo);
    }

    public List<Todo> getPaginatedTodoList(Integer page, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "creationDateTime");
        int jpaPageNum = page - 1;
        int jpaPageSize = pageSize;
        Page<Todo> todosPage = repository.findAll(PageRequest.of(jpaPageNum, jpaPageSize, sort));
        if( !todosPage.isEmpty() ){
            return todosPage.toList();
        }
        throw new NoSuchTodoException("list of todos is empty");
    }

    public Todo getTodoById(UUID todoId) {
        return repository.findById(todoId).orElseThrow( () -> new NoSuchTodoException("there is no such todo") );
    }


}
