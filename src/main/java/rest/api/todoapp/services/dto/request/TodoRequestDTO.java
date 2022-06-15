package rest.api.todoapp.services.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoRequestDTO {
    @JsonProperty(required = true)
    private String title;
    @JsonProperty(required = true)
    private String body;
}
