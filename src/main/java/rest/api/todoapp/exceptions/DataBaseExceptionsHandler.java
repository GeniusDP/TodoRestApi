package rest.api.todoapp.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rest.api.todoapp.services.dto.ExceptionResponseEntity;

import java.sql.SQLException;
import java.time.LocalDateTime;

@ControllerAdvice
public class DataBaseExceptionsHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ExceptionResponseEntity> handleDataAccessException(DataAccessException exception){
        String comment = "Error during data processing.";
        ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(comment, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponseEntity);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionResponseEntity> handleSQLException(){
        String comment = "Connection successful, but error during querying.";
        ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(comment, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponseEntity);
    }

    @ExceptionHandler(NoElementsException.class)
    public ResponseEntity<ExceptionResponseEntity> noElementsExceptionHandler(NoElementsException exception){
        String comment = exception.getLocalizedMessage();
        ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(comment, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(exceptionResponseEntity);
    }


}
