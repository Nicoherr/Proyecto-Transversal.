package com.marketplace.pago.DTO;
@Data //Getter and Setter
@AllArgsConstructor //Constructores con parametros
@NoArgsConstructor //Constructores sin parametros
public class PagoNewDTO {
    //Recibe los datos del usuario al crear un Reporte.
    //Usamos los campos de la clase reporte pero solo los que deve llenar un usuario por la Api
    @NotBlank(message = "Escribe el metodo de pago")
    @Column(nullable = false, length = 20)//Validamos que el dato no sea nulo en la Base de Datos.
    private String metodoPago;
}
