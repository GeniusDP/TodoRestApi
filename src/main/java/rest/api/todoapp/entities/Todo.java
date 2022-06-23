package rest.api.todoapp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Todo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID todoId;
    private String title;
    private String body;
    private LocalDateTime creationDateTime;
    private LocalDateTime lastUpdateDateTime;

    public Todo(UUID todoId, String title, String body, LocalDateTime creationDateTime, LocalDateTime lastUpdateDateTime) {
        this.todoId = todoId;
        this.title = title;
        this.body = body;
        this.creationDateTime = creationDateTime;
        this.lastUpdateDateTime = lastUpdateDateTime;
    }
}
