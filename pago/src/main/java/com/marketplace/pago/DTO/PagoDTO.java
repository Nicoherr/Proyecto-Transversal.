package com.marketplace.pago.DTO;

import java.util.Date;

@Data //Getter and Setter
@AllArgsConstructor //Constructores con parametros
@NoArgsConstructor //Constructores sin parametros
public class PagoDTO {
    //Devuelve datos al usuario como respuesta
    //Usamos lo mismo de la clase Reporte pero sin la notaciones JPA y sin @Entity y @Table.
    private long id = 0;

    @NotBlank(message = "Escribe el metodo de pago")
    @Column(nullable = false, length = 20)//Validamos que el dato no sea nulo en la Base de Datos.
    private String metodoPago;


    @Column(nullable = false)//Validamos que el dato no sea nulo en la Base de Datos.
    private String comprobante;

    @Column(nullable = false)//Validamos que el dato no sea nulo en la Base de Datos.
    private Date fecha;
}
