package com.marketplace.pago.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data //Getter and Setter
@AllArgsConstructor //Constructores con parametros
@NoArgsConstructor //Constructores sin parametros
public class PagoResponseDTO {
    //Devuelve datos al usuario como respuesta
    //Usamos los campos de la clase reporte pero solo los que deve llenar un usuario por la Api
    private long id = 0;

    @NotBlank(message = "Escribe el metodo de pago")
    private String metodoPago;

    private String comprobante;

    private Date fecha;
}
