package rest.api.todoapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import rest.api.todoapp.entities.Todo;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, UUID> {

    @Modifying
    @Transactional
    @Query(value = "insert into sandbox.public.todos(todo_id, title, body, done, creation_date_time, last_update_date_time) " +
            "VALUES (:todoId, :title, :body, false, :creationDateTime, :creationDateTime)", nativeQuery=true)
    void saveWithProvidedId_Native(@Param("todoId") UUID todoId,
                                   @Param("title") String title,
                                   @Param("body") String body,
                                   @Param("creationDateTime") LocalDateTime creationDateTime);

}
