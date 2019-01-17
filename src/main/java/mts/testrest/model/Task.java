package mts.testrest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @JsonIgnore
    private String id;
    private String status;
    private LocalDateTime timestamp;

    public enum Status {
        CREATED,
        RUNNING,
        FINISHED
    }
}
