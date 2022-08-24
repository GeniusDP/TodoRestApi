package rest.api.todoapp.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import rest.api.todoapp.exceptions.validation.Violation;

import java.util.List;

@Data
public class ValidationExceptionResponse {

    private List<Violation> violations;

    public ValidationExceptionResponse(List<Violation> violations) {
        this.violations = violations;
    }

}
