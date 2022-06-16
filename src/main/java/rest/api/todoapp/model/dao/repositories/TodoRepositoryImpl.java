package rest.api.todoapp.model.dao.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rest.api.todoapp.exceptions.NoSuchTodoException;
import rest.api.todoapp.model.dao.extractors.AllTodosExtractor;
import rest.api.todoapp.model.entities.Todo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("todoRepository")
@Transactional
public class TodoRepositoryImpl implements TodoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Todo> getAllTodos() {
        String sql = "SELECT * FROM todos;";
        return jdbcTemplate.query( sql, new AllTodosExtractor() );
    }

    @Override
    public UUID updateTodo(UUID todoId, Optional<String> title, Optional<String> body) {
        String sqlTitle = "UPDATE todos SET title = ? WHERE todo_id = ?";
        String sqlBody = "UPDATE todos SET body = ? WHERE todo_id = ?";
        String sqlUpdateTime = "UPDATE todos SET last_update_date_time = ? WHERE todo_id = ?";

        title.ifPresent(value -> jdbcTemplate.update(sqlTitle, value, todoId));
        body.ifPresent(value -> jdbcTemplate.update(sqlBody, value, todoId));
        int rowsEffected = jdbcTemplate.update(sqlUpdateTime, LocalDateTime.now(), todoId);

        if( rowsEffected == 0 ){
            throw new NoSuchTodoException("Error! There is now todo with such todoId!");
        }

        return todoId;
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
