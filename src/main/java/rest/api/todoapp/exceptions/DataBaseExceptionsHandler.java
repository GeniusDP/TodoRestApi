package rest.api.todoapp.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class DataBaseExceptionsHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(){
        String comment = "Error during data extracting.";
        return ResponseEntity.status(500).body(comment);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQLException(){
        String comment = "Connection successful, but error during querying.";
        return ResponseEntity.status(500).body(comment);
    }
}
