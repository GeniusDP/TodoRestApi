package rest.api.todoapp.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static rest.api.todoapp.Constants.SwaggerDescriptions.*;

@Data
public class PaginationRequestTodoDTO {

    @Min(value = 1, message = "page should be bigger or equal to 1")
    @Schema(description = PAGE_NUMBER)
    private Integer page = 1;

    @Min(value = 5, message = "pageSuze should be bigger or equals to 5")
    @Max(value = 30, message = "pageSuze should be less or equals to 30")
    @Schema(description = PAGE_SIZE)
    private Integer pageSize = 5;

}
