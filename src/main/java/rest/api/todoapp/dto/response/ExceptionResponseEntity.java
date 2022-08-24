package rest.api.todoapp.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

import static rest.api.todoapp.Constants.SwaggerDescriptions.*;

@Data
public final class ExceptionResponseEntity {

    @Schema(description = ERROR_MESSAGE)
    private final String message;

    @Schema(description = ERROR_CAUSED_TIME)
    private final LocalDateTime exceptionCauseTime;

    public ExceptionResponseEntity(String message, LocalDateTime exceptionCauseTime) {
        this.message = message;
        this.exceptionCauseTime = exceptionCauseTime;
    }


}
