package rest.api.todoapp.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rest.api.todoapp.services.dto.response.ExceptionResponseEntity;

import java.sql.SQLException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoElementsException.class)
    public ResponseEntity<ExceptionResponseEntity> noElementsExceptionHandler(NoElementsException exception){
        String comment = exception.getLocalizedMessage();
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ExceptionResponseEntity(comment, LocalDateTime.now()));
    }

    @ExceptionHandler(NoSuchTodoException.class)
    public ResponseEntity<ExceptionResponseEntity> noSuchTodoExceptionHandler(NoSuchTodoException e){
        String comment = e.getMessage();
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body( new ExceptionResponseEntity(comment, LocalDateTime.now()) );
    }





    @ExceptionHandler({DataAccessException.class, SQLException.class})
    public ResponseEntity<ExceptionResponseEntity> dataAccessExceptionHandler(DataAccessException exception){
        String comment = "Error during data processing or accessing.";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body( new ExceptionResponseEntity(comment, LocalDateTime.now()) );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseEntity> illegalArgumentAndNullPointerExceptionHandler(){
        String comment = "You cannot pass such values of parameters!";
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body( new ExceptionResponseEntity(comment, LocalDateTime.now()) );
    }

    @ExceptionHandler(NotAllParametersInUrlException.class)
    public ResponseEntity<ExceptionResponseEntity> notAllParametersInUrlExceptionHandler(NotAllParametersInUrlException e){
        String comment = e.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body( new ExceptionResponseEntity(comment, LocalDateTime.now()) );
    }

}
