package rest.api.todoapp.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static rest.api.todoapp.Constants.SwaggerDescriptions.*;

@Data
public class UpdateTodoRequestDTO {

    @NotNull
    @Schema(description = TODO_ID)
    private final UUID todoId;

    @Schema(description = TITLE)
    private final String title;

    @Schema(description = BODY)
    private final String body;

    @Schema(description = DONE_STATUS)
    private final Boolean done;

    public UpdateTodoRequestDTO(UUID todoId, String title, String body, Boolean done) {
        this.todoId = todoId;
        this.title = title;
        this.body = body;
        this.done = done;
    }

}
