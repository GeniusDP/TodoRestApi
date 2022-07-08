package rest.api.todoapp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "todos")
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Todo {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "todo_id")
    private UUID todoId;

    @Column(name = "title")
    @Setter
    private String title;

    @Column (name = "body")
    @Setter
    private String body;

    @Column(name = "done")
    @Setter
    private Boolean done = false;

    @Column(name = "creation_date_time")
    @CreationTimestamp
    private LocalDateTime creationDateTime;

    @Column(name = "last_update_date_time")
    @UpdateTimestamp
    private LocalDateTime lastUpdateDateTime;

    public Todo(String title, String body, Boolean done) {
        this.title = title;
        this.body = body;
        this.done = done;
    }


}
