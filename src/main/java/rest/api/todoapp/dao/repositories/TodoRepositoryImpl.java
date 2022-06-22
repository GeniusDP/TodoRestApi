package rest.api.todoapp.dao.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rest.api.todoapp.dao.extractors.AllTodosRowMapper;
import rest.api.todoapp.dao.extractors.GetCreationDateRowMapper;
import rest.api.todoapp.exceptions.NoSuchTodoException;
import rest.api.todoapp.entities.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        boolean wasUpdated = false;

        if( todo.getTitle() != null ){
            wasUpdated = true;
            updateTodoField(todo.getTodoId(),"title", todo.getTitle());
        }

        if( todo.getBody() != null ){
            wasUpdated = true;
            updateTodoField(todo.getTodoId(),"body", todo.getBody());
        }

        if(wasUpdated){
            String sqlUpdateTime = "UPDATE todos SET last_update_date_time = ? WHERE todo_id = ?";
            jdbcTemplate.update(sqlUpdateTime, LocalDateTime.now(), todo.getTodoId());
        }

        String sqlCreatingTime = "SELECT creation_date_time FROM todos WHERE todo_id = ?";
        LocalDateTime creation_date_time = jdbcTemplate.query(sqlCreatingTime, new GetCreationDateRowMapper(), todo.getTodoId()).get(0);
        todo.setCreationDateTime(creation_date_time);
        todo.setLastUpdateDateTime(LocalDateTime.now());
        return todo;
    }

    private void updateTodoField(UUID todoId, String fieldName, String newValue) {
        jdbcTemplate.update("UPDATE todos SET ? = ? WHERE todo_id = ?", fieldName, newValue, todoId);
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
    public Todo saveTodo(String title, String body) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("todos")
                .usingGeneratedKeyColumns("todo_id");
        Map<String, Object> properties = new HashMap<>();
        properties.put("title", title);
        properties.put("body", body);
        properties.put("creation_date_time", LocalDateTime.now());
        properties.put("last_update_date_time", LocalDateTime.now());
        KeyHolder keyHolder = simpleJdbcInsert.executeAndReturnKeyHolder(properties);
        return getTodoById( keyHolder.getKeyAs(UUID.class) );
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, title);
//            ps.setString(2, body);
//            return ps;
//        }, keyHolder);
//        return getTodoById( keyHolder.getKeyAs(UUID.class) );
    }

    @Override
    public Todo getTodoById(UUID todoId) {
        String sql = "SELECT * FROM todos WHERE todo_id = ?;";
        List<Todo> todoList = jdbcTemplate.query(sql, new AllTodosRowMapper(), todoId);
        if( todoList.isEmpty() ){
            throw new NoSuchTodoException("there is no such todo");
        }
        return todoList.get(0);
    }

}
