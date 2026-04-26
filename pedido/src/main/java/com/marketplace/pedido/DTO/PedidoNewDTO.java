package com.marketplace.pedido.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoNewDTO {
        //Recibe los datos del usuario al crear un Reporte.
        //Usamos los campos de la clase reporte pero solo los que deve llenar un usuario por la Api
        @NotBlank(message = "Ingresa el nombre del producto")
        @Column(nullable = false, lenght = 200)//Validamos que el dato no sea nulo en la Base de Datos.
        private String nomProducto;

        @NotBlank(message = "Deves especificar el tipo de producto")
        @Column(nullable = false, lenght = 30)//Validamos que el dato no sea nulo en la Base de Datos.
        private String tipoProducto;

        @NotNull(message = "Deves ingresar el precio del producto que deseas vender")
        @Column(nullable = false)//Validamos que el dato no sea nulo en la Base de Datos.
        private int precio;


}
