package rest.api.todoapp.services.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ExceptionResponseEntity {
    @JsonProperty(required = true)
    private final String message;
    @JsonProperty(required = true)
    private final LocalDateTime exceptionCauseTime;

    public ExceptionResponseEntity(String message, LocalDateTime exceptionCauseTime) {
        this.message = message;
        this.exceptionCauseTime = exceptionCauseTime;
    }

}
