package rest.api.todoapp.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ExceptionResponseEntity {
    @JsonProperty
    private final String message;
    @JsonProperty
    private final LocalDateTime exceptionCauseTime;
}
