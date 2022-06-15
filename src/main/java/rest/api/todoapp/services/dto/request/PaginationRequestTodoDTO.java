package rest.api.todoapp.services.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaginationRequestTodoDTO {
    @JsonProperty(required = true)
    private Long page;
    @JsonProperty(required = true)
    private Long pageSize;

}
