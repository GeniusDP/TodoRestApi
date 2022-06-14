package rest.api.todoapp.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoUpdateParameters {
    private String title;
    private String body;
}
