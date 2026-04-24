package com.marketplace.notificacion.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionNewDTO {
    @NotBlank(message = "El asunto deve estar descrito")
    private String asunto;
    @NotNull(message = "El mensaje no puede estar vacio")
    private String mensaje;
}
