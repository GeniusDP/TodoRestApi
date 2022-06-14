package rest.api.todoapp.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Todo {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String title;
    private String body;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;

    public Todo() {
    }

    public Todo(String title, String body, LocalDateTime creationDate, LocalDateTime lastUpdateDate) {
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }


}
