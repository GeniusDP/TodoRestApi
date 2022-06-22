package rest.api.todoapp.dto.request;


import javax.validation.constraints.NotNull;
import java.util.UUID;

public class TestUuid {
    @NotNull
    private UUID uuid;
}
