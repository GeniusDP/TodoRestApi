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
    public int updateTodo(long todoId, Optional<String> title, Optional<String> body) {
        String sqlTitle = "UPDATE sandbox.public.todos SET title = ? WHERE todo_id = ?";
        String sqlBody = "UPDATE sandbox.public.todos SET body = ? WHERE todo_id = ?";
        int cnt = 0;
        if( title.isPresent() ){
            cnt += jdbcTemplate.update(sqlTitle, title.get(), todoId);
        }

        if( body.isPresent() ){
            cnt += jdbcTemplate.update(sqlBody, body.get(), todoId);
        }
        return cnt > 0 ? 1 : 0;
    }

    @Override
    public int deleteTodo(long todoId) {
        String sql = "DELETE FROM sandbox.public.todos WHERE todo_id = ?;";
        return jdbcTemplate.update(sql, todoId);
    }

    @Override
    public void saveTodo(String title, String body) {
        String sql = "INSERT INTO sandbox.public.todos (title, body) VALUES (?, ?);";
        jdbcTemplate.update(sql, body);
    }

}
