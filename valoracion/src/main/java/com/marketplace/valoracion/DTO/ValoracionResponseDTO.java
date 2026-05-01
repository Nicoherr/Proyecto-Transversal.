package com.marketplace.valoracion.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Getter and Setter
@AllArgsConstructor //Constructores con parametros
@NoArgsConstructor //Constructores sin parametros
public class ValoracionResponseDTO {
    //Devuelve datos al usuario como respuesta
    //Usamos lo mismo de la clase Reporte pero sin la notaciones JPA y sin @Entity y @Table.
    private long id = 0;

    @NotNull(message = "El campo no puede ser nulo")
    private int numEstrella;

    @NotBlank(message = "Ingresa una recomendacion del producto")
    private String recomendacion;

}
