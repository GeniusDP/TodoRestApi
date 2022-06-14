package rest.api.todoapp.model.dao.extractors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import rest.api.todoapp.model.entities.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AllTodosExtractor implements ResultSetExtractor<List<Todo>> {

    @Override
    public List<Todo> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<Todo> lst = new ArrayList<>();
        while( resultSet.next() ){
            long todoId = resultSet.getLong("todo_id");
            String todoTitle = resultSet.getString( "title");
            String todoBody = resultSet.getString( "body");
            LocalDateTime createdDateTime = resultSet.getTimestamp( "creation_date_time" ).toLocalDateTime();
            LocalDateTime lastUpdateDateTime = resultSet.getTimestamp( "last_update_date_time" ).toLocalDateTime();
            Todo todo = new Todo(todoId, todoTitle, todoBody, createdDateTime, lastUpdateDateTime);
            lst.add( todo );
        }
        return lst.size()>0 ? lst : null;

    }

}
