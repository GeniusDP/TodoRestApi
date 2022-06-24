package rest.api.todoapp.dao.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rest.api.todoapp.dao.extractors.AllTodosRowMapper;
import rest.api.todoapp.entities.Todo;
import rest.api.todoapp.exceptions.NoSuchTodoException;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
        if( getTodoById( todo.getTodoId() ) == null ){
            return saveTodo( todo.getTitle(), todo.getBody() );
        }

        boolean wasUpdated = false;

        if( todo.getTitle() != null ){
            wasUpdated = true;
            jdbcTemplate.update("UPDATE todos SET title = ? WHERE todo_id = ?;", todo.getTitle(), todo.getTodoId());
        }

        if( todo.getBody() != null ){
            wasUpdated = true;
            jdbcTemplate.update("UPDATE todos SET body = ? WHERE todo_id = ?;", todo.getBody(), todo.getTodoId());
        }

        if( todo.getDone() != null ){
            wasUpdated = true;
            jdbcTemplate.update("UPDATE todos SET done = ? WHERE todo_id = ?;", todo.getDone(), todo.getTodoId());
        }

        if(wasUpdated){
            String sqlUpdateTime = "UPDATE todos SET last_update_date_time = ? WHERE todo_id = ?";
            jdbcTemplate.update(sqlUpdateTime, LocalDateTime.now(), todo.getTodoId());
        }

        todo = getTodoById(todo.getTodoId());
        todo.setLastUpdateDateTime(LocalDateTime.now());
        return todo;
    }

    @Override
    public Todo deleteTodo(UUID todoId) {
        Todo todoReturning = getTodoById(todoId);
        String sql = "DELETE FROM todos WHERE todo_id = ?;";
        if( jdbcTemplate.update(sql, todoId) == 0 ){
            throw new NoSuchTodoException("Error! There is now todo with such todoId!");
        }
        return todoReturning;
    }


    @Override
    public Todo saveTodo(String title, String body) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO todos(title, body) VALUES (?, ?) RETURNING todo_id;";
        jdbcTemplate.update((con) -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, title);
            ps.setString(2, body);
            return ps;
        }, keyHolder);
        return getTodoById( keyHolder.getKeyAs(UUID.class) );
    }

    @Override
    public Todo getTodoById(UUID todoId) {
        String sql = "SELECT * FROM todos WHERE todo_id = ?;";
        List<Todo> todoList = jdbcTemplate.query(sql, new AllTodosRowMapper(), todoId);
        return todoList.isEmpty() ? null : todoList.get(0);
    }

    @Override
    public List<Todo> getPaginatedTodoList(Long page, Long pageSize) {
        String sql = "SELECT * FROM todos OFFSET ? LIMIT ?;";
        return jdbcTemplate.query(sql, new AllTodosRowMapper(), (page - 1) * pageSize, pageSize);
    }

}
