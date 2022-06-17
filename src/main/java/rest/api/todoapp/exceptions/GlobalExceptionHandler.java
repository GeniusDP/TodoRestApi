package rest.api.todoapp.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import rest.api.todoapp.services.dto.response.ExceptionResponseEntity;

import java.sql.SQLException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoElementsException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ExceptionResponseEntity noElementsExceptionHandler(NoElementsException exception){
        return new ExceptionResponseEntity(exception.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NoSuchTodoException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ExceptionResponseEntity noSuchTodoExceptionHandler(NoSuchTodoException e){
        return new ExceptionResponseEntity(e.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler({DataAccessException.class, SQLException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponseEntity dataAccessExceptionHandler(){
        return new ExceptionResponseEntity("Error during data processing or accessing.", LocalDateTime.now());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponseEntity illegalArgumentAndNullPointerExceptionHandler(){
        return new ExceptionResponseEntity("You cannot pass such values of parameters!", LocalDateTime.now());
    }

    @ExceptionHandler(NotAllParametersInUrlException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponseEntity notAllParametersInUrlExceptionHandler(NotAllParametersInUrlException e){
        return new ExceptionResponseEntity(e.getMessage(), LocalDateTime.now());
    }

}
