package com.marketplace.pago.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data //Getter and Setter
@AllArgsConstructor //Constructores con parametros
@NoArgsConstructor //Constructores sin parametros
public class PagoRequestDTO {
    //Recibe los datos del usuario al crear un Reporte.
    //Usamos lo mismo de la clase Reporte pero sin la notaciones JPA y sin @Entity y @Table.
    @NotBlank(message = "Escribe el metodo de pago")
    private String metodoPago;
}
