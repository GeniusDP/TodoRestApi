package rest.api.todoapp.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotBlank;

import static rest.api.todoapp.Constants.SwaggerDescriptions.*;

@Data
public class SaveTodoRequestDTO {

    @NotBlank(message = "title should not be null")
    @Schema(description = TITLE)
    private String title;

    @NotBlank(message = "body should not be null")
    @Schema(description = BODY)
    private String body;

}
