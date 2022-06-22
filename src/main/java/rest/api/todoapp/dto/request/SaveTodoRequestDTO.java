package rest.api.todoapp.dto.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class SaveTodoRequestDTO {

    @NotBlank(message = "title should not be null")
    private String title;

    @NotBlank(message = "body should not be null")
    private String body;

}
