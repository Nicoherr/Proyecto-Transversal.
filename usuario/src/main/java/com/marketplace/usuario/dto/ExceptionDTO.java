package com.marketplace.usuario.dto;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data
public class ExceptionDTO {
    private int status;
    private String error;
    private String message;
    private LocalDateTime timestamp;

    // Este es el constructor que usan los Handlers
    public ExceptionDTO(HttpStatus status, String message) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}

