package rest.api.todoapp.services.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TodoRequestDTO {
    @JsonProperty(required = true)
    private UUID todoId;

    @JsonProperty(required = true)
    private String title;

    @JsonProperty(required = true)
    private String body;
}
