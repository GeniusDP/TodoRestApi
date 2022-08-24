package rest.api.todoapp.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import rest.api.todoapp.dto.response.ValidationExceptionResponse;
import rest.api.todoapp.exceptions.validation.Violation;
import rest.api.todoapp.dto.response.ExceptionResponseEntity;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchTodoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponseEntity noSuchTodoExceptionHandler(NoSuchTodoException e){
        return new ExceptionResponseEntity("No such todo found: " + e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MissedRequiredArgumentsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponseEntity lackOfParametersToSaveTodoExceptionHandler(MissedRequiredArgumentsException e){
        return new ExceptionResponseEntity("Not all required arguments are present: " + e.getMessage(),
                LocalDateTime.now());
    }


    @ExceptionHandler({DataAccessException.class, SQLException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponseEntity dataAccessExceptionHandler(){
        String comment = "Error from database (for example FK duplicate; also title should be unique)";
        return new ExceptionResponseEntity(comment, LocalDateTime.now());
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ValidationExceptionResponse methodArgumentNotValidExceptionHandle(BindException exception){
        List<Violation> violations = exception.getAllErrors().stream().map(objectError -> {
            return new Violation(((FieldError) objectError).getField(), objectError.getDefaultMessage());
        }).toList();
        return new ValidationExceptionResponse(violations);
    }


}
