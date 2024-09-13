package com.kodhnk.base.log;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

@Data
@Document(collection = "logs")
public class LogEntry {
    @Id
    private String id;
    private String level;
    private String message;
    private LocalDateTime timestamp;
    private String method;
}
