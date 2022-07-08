package rest.api.todoapp.exceptions;

public class MissedRequiredArgumentsException extends RuntimeException{

    public MissedRequiredArgumentsException(String message) {
        super(message);
    }

}
