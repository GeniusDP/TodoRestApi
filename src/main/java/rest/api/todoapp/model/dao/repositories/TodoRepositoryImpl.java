package rest.api.todoapp.model.dao.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rest.api.todoapp.model.dao.extractors.AllTodosExtractor;
import rest.api.todoapp.model.entities.Todo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository("todoRepository")
@Transactional
public class TodoRepositoryImpl implements TodoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Todo> getAllTodos() {
        String sql = "SELECT * FROM sandbox.public.todos;";
        return jdbcTemplate.query( sql, new AllTodosExtractor() );
    }

    @Override
    public long updateTodo(long todoId, Optional<String> title, Optional<String> body) {
        String sqlTitle = "UPDATE sandbox.public.todos SET title = ? WHERE todo_id = ?";
        String sqlBody = "UPDATE sandbox.public.todos SET body = ? WHERE todo_id = ?";

        title.ifPresent(value -> jdbcTemplate.update(sqlTitle, value, todoId));
        body.ifPresent(value -> jdbcTemplate.update(sqlBody, value, todoId));

        String sqlUpdateTime = "UPDATE sandbox.public.todos SET last_update_date_time = ? WHERE todo_id = ?";
        jdbcTemplate.update(sqlUpdateTime, LocalDateTime.now(), todoId);
        return todoId;
    }

    @Override
    public long deleteTodo(long todoId) {
        String sql = "DELETE FROM sandbox.public.todos WHERE todo_id = ?;";
        jdbcTemplate.update(sql, todoId);
        return todoId;
    }

    @Override
    public long saveTodo(String title, String body) {
        String sql = "INSERT INTO sandbox.public.todos (title, body) VALUES (?, ?);";
        jdbcTemplate.update(sql, title, body);
        return getAllTodos().stream().filter( todo -> todo.getTitle().equals(title) ).findAny().get().getTodoId();
    }

}
