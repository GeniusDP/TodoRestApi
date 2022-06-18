package rest.api.todoapp.services.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationRequestTodoDTO {

    @JsonProperty(required = true)
    private Long page = 1L;

    @JsonProperty(required = true)
    private Long pageSize = 5L;

}
