package rest.api.todoapp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static rest.api.todoapp.Constants.SwaggerDescriptions.*;

@Getter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "todos")
@Schema(description = TODO)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Todo {

    @Id
    @GeneratedValue
    @Column(name = "todo_id")
    @Schema(description = TODO_ID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID todoId;

    @Setter
    @Column(name = "title")
    @Schema(description = TITLE)
    private String title;

    @Setter
    @Column (name = "body")
    @Schema(description = BODY)
    private String body;

    @Setter
    @Column(name = "done")
    @Schema(description = DONE_STATUS)
    private Boolean done = false;

    @CreationTimestamp
    @Column(name = "creation_date_time")
    @Schema(description = CREATION_TIME)
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    @Column(name = "last_update_date_time")
    @Schema(description = LAST_UPDATE_TIME)
    private LocalDateTime lastUpdateDateTime;

    public Todo(String title, String body, Boolean done) {
        this.title = title;
        this.body = body;
        this.done = done;
    }


}
