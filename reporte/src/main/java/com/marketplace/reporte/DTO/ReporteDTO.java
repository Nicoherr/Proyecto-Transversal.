package com.marketplace.reporte.DTO;

import java.util.Date;

@Data //Getter and Setter
@AllArgsConstructor //Constructores con parametros
@NoArgsConstructor //Constructores sin parametros
public class ReporteDTO {
    //Devuelve datos al usuario como respuesta
    //Usamos lo mismo de la clase Reporte pero sin la notaciones JPA y sin @Entity y @Table.
    private long id = 0;

    @NotBlank(message = "Ingresa un tipo de reporte valido")
    @Column(nullable = false, length= 100) //Validamos que el dato no sea nulo en la Base de Datos.
    private String tipo;

    @NotBlank(message = "La descripcion del Reporte es obligatoria")
    @Column(nullable = false, length = 512)//Validamos que el dato no sea nulo en la Base de Datos.
    private String descripcion;

    //Cuando se genera un reporte el sistema le asigna una fecha en el momento
    @Column(nullable = false)//Validamos que el dato no sea nulo en la Base de Datos.
    private Date fecha;

    //El sistema asigna por defecto el estado
    @Column(nullable = false)//Validamos que el dato no sea nulo en la Base de Datos.
    private Boolean estado;
}
