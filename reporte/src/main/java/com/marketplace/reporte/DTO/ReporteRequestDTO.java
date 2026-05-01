package com.marketplace.reporte.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data //Getter and Setter
@AllArgsConstructor //Constructores con parametros
@NoArgsConstructor //Constructores sin parametros
public class ReporteRequestDTO {
    //Recibe los datos del usuario al crear un Reporte.
    //Usamos los campos de la clase reporte pero solo los que deve llenar un usuario por la Api
    @NotBlank(message = "Ingresa un tipo de reporte valido")
    private String tipo;

    @NotBlank(message = "La descripcion del Reporte es obligatoria")
    private String descripcion;

}
