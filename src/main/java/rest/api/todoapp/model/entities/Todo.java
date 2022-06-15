package rest.api.todoapp.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Todo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID todoId;
    private String title;
    private String body;
    private LocalDateTime creationDateTime;
    private LocalDateTime lastUpdateDateTime;

    public Todo() {
    }


    public Todo(UUID todoId, String title, String body, LocalDateTime creationDateTime, LocalDateTime lastUpdateDateTime) {
        this.todoId = todoId;
        this.title = title;
        this.body = body;
        this.creationDateTime = creationDateTime;
        this.lastUpdateDateTime = lastUpdateDateTime;
    }
}
