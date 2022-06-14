package rest.api.todoapp.model.dao.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rest.api.todoapp.model.dao.extractors.AllTodosExtractor;
import rest.api.todoapp.model.entities.Todo;

import java.util.List;
import java.util.Optional;

@Repository("todoRepository")
@Transactional
public class TodoRepositoryImpl implements TodoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<List<Todo>> getAllTodos() {
        String sql = "SELECT * FROM sandbox.public.todos;";
        return Optional.ofNullable( jdbcTemplate.query( sql, new AllTodosExtractor() ) );
    }

    @Override
    public void updateTodo(long todoId) {

    }

    @Override
    public int deleteTodo(long todoId) {
        String sql = "DELETE FROM sandbox.public.todos WHERE todo_id = ?;";
        return jdbcTemplate.update(sql, todoId);
    }

    @Override
    public void saveTodo(String title, String body) {
        String sql = "insert into sandbox.public.todos (title, body) values (?, ?);";
        jdbcTemplate.update(sql, body);
    }

}
