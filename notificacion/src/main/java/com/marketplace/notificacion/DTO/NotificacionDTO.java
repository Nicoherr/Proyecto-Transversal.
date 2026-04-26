package com.marketplace.notificacion.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data //Getter and Setter
@AllArgsConstructor //Constructores con parametros
@NoArgsConstructor //Constructores sin parametros
public class NotificacionDTO {
    //Devuelve datos al usuario como respuesta
    //Usamos lo mismo de la clase Reporte pero sin la notaciones JPA y sin @Entity y @Table.
    private long id = 0;

    @NotBlank(message = "El asunto deve estar descrito")
    @Column(nullable = false, length = 100)//Validamos que el dato no sea nulo en la Base de Datos.
    private String asunto;

    @NotBlank(message = "El mensaje no puede estar vacio")
    @Column(nullable = false, length = 512)//Validamos que el dato no sea nulo en la Base de Datos.
    private String mensaje;

    @Column(nullable = false)//Validamos que el dato no sea nulo en la Base de Datos.
    private Date fecha;
}
