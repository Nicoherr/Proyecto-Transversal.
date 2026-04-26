package com.marketplace.valoracion.DTO;
@Data //Getter and Setter
@AllArgsConstructor //Constructores con parametros
@NoArgsConstructor //Constructores sin parametros
public class ValoracionDTO {
    //Devuelve datos al usuario como respuesta
    //Usamos lo mismo de la clase Reporte pero sin la notaciones JPA y sin @Entity y @Table.
    private long id = 0;

    @NotNull(message = "El campo no puede ser nulo")
    @Column(nullable = false)//Validamos que el dato no sea nulo en la Base de Datos.
    private int numEstrella;

    @NotBlank(message = "Ingresa una recomendacion del producto")
    @Column(nullable = false, length = 512)//Validamos que el dato no sea nulo en la Base de Datos.
    private String recomendacion;
}
