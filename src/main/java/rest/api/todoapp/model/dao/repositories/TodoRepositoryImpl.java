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
public class TodoRepositoryImpl implements TodoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<List<Todo>> getAllTodos() {
        String sql = "SELECT * FROM sandbox.public.todos;";
        return Optional.ofNullable( jdbcTemplate.query( sql, new AllTodosExtractor() ) );
    }

    @Override
    @Transactional
    public void updateTodo(long todoId) {

    }

    @Override
    @Transactional
    public void deleteTodo(long todoId) {

    }

    @Override
    public void saveTodo(Todo todo) {

    }

}
