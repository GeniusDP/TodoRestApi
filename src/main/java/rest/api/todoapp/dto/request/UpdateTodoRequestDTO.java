package rest.api.todoapp.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UpdateTodoRequestDTO {
    @NotNull
    private final UUID todoId;
    private final String title;
    private final String body;
    private final Boolean done;

    public UpdateTodoRequestDTO(UUID todoId, String title, String body, Boolean done) {
        this.todoId = todoId;
        this.title = title;
        this.body = body;
        this.done = done;
    }

}
