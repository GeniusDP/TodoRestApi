package rest.api.todoapp.exceptions;

public class NoElementsException extends RuntimeException {
    public NoElementsException(String message) {
        super(message);
    }

}
