package rest.api.todoapp.model.dao.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rest.api.todoapp.exceptions.NoSuchTodoException;
import rest.api.todoapp.model.dao.extractors.AllTodosRowMapper;
import rest.api.todoapp.model.dao.extractors.GetCreationDateRowMapper;
import rest.api.todoapp.model.entities.Todo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository("todoRepository")
@Transactional
public class TodoRepositoryImpl implements TodoRepository {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public TodoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Todo> getAllTodos() {
        String sql = "SELECT * FROM todos;";
        return jdbcTemplate.query( sql, new AllTodosRowMapper() );
    }

    @Override
    public Todo updateTodo(Todo todo) {
        String sqlTitle = "UPDATE todos SET title = ? WHERE todo_id = ?";
        String sqlBody = "UPDATE todos SET body = ? WHERE todo_id = ?";
        String sqlUpdateTime = "UPDATE todos SET last_update_date_time = ? WHERE todo_id = ?";
        String sqlCreatingTime = "SELECT creation_date_time FROM todos WHERE todo_id = ?";

        jdbcTemplate.update(sqlTitle, todo.getTitle(), todo.getTodoId());
        jdbcTemplate.update(sqlBody, todo.getBody(), todo.getTodoId());
        jdbcTemplate.update(sqlUpdateTime, LocalDateTime.now(), todo.getTodoId());

        LocalDateTime creation_date_time = jdbcTemplate.query(sqlCreatingTime, new GetCreationDateRowMapper(), todo.getTodoId()).get(0);
        todo.setCreationDateTime(creation_date_time);
        todo.setLastUpdateDateTime(LocalDateTime.now());
        return todo;
    }

    @Override
    public UUID deleteTodo(UUID todoId) {
        String sql = "DELETE FROM todos WHERE todo_id = ?;";
        int rowsEffected = jdbcTemplate.update(sql, todoId);
        if( rowsEffected == 0 ){
            throw new NoSuchTodoException("Error! There is now todo with such todoId!");
        }
        return todoId;
    }

    @Override
    public UUID saveTodo(String title, String body) {
        String sql = "INSERT INTO todos (title, body) VALUES (?, ?);";
        jdbcTemplate.update(sql, title, body);
        return getAllTodos().stream().filter( todo -> todo.getTitle().equals(title) ).findAny().get().getTodoId();
    }

}
