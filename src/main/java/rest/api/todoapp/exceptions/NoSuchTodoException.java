package rest.api.todoapp.exceptions;

public class NoSuchTodoException extends RuntimeException {
    public NoSuchTodoException(String message) {
        super(message);
    }
}
