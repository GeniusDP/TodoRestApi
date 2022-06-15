package rest.api.todoapp.exceptions;

public class NotAllParametersInUrlException extends RuntimeException {
    public NotAllParametersInUrlException(String message) {
        super(message);
    }

}
