package rest.api.todoapp.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public final class ExceptionResponseEntity {
    private final String message;
    private final LocalDateTime exceptionCauseTime;

    public ExceptionResponseEntity(String message, LocalDateTime exceptionCauseTime) {
        this.message = message;
        this.exceptionCauseTime = exceptionCauseTime;
    }


}
