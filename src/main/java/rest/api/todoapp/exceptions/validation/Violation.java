package rest.api.todoapp.exceptions.validation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static rest.api.todoapp.Constants.SwaggerDescriptions.*;

@Data
public final class Violation {

    @Schema(description = FIELD_NAME)
    private final String fieldName;

    @Schema(description = ERROR_MESSAGE)
    private final String message;

    public Violation(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }


}
