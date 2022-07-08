package rest.api.todoapp.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

@Data
public class PaginationRequestTodoDTO {

    @Min(value = 1, message = "page should be bigger or equal to 1")
    private Integer page = 1;

    @Range(min = 5, max = 200, message = "pageSuze should be from 5 to 200")
    private Integer pageSize = 5;

}
