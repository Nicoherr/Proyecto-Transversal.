package com.marketplace.pago.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "pago")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int monto;

    @NotBlank(message = "Escribe el metodo de pago")
    @Column
    private String metodoPago;

    @NotBlank(message = "Ingresa un comprobante valido")
    @Column
    private String comprobante;

    @NotBlank(message = "Ingresa una fecha valida")
    @Column
    private String fecha;

}
