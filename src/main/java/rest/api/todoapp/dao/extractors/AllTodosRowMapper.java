package rest.api.todoapp.dao.extractors;

import org.springframework.jdbc.core.RowMapper;
import rest.api.todoapp.entities.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class AllTodosRowMapper implements RowMapper<Todo> {

    @Override
    public Todo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UUID todoId = UUID.fromString( resultSet.getString("todo_id") );
        String todoTitle = resultSet.getString( "title");
        String todoBody = resultSet.getString( "body");
        Boolean done = resultSet.getBoolean("done");
        LocalDateTime createdDateTime = resultSet.getTimestamp( "creation_date_time" ).toLocalDateTime();
        LocalDateTime lastUpdateDateTime = resultSet.getTimestamp( "last_update_date_time" ).toLocalDateTime();
        return new Todo(todoId, todoTitle, todoBody, done, createdDateTime, lastUpdateDateTime);
    }

}
