package rest.api.todoapp.model.dao.extractors;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class GetCreationDateRowMapper implements RowMapper<LocalDateTime> {
    @Override
    public LocalDateTime mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getTimestamp("creation_date_time").toLocalDateTime();
    }
}
