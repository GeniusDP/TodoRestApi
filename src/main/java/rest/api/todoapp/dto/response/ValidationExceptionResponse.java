package rest.api.todoapp.dto.response;

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
