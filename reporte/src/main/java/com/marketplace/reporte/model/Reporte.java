package com.marketplace.reporte.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reporte")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    @NotBlank(message = "Ingresa un texto valido")
    private String tipo;


    @Column(nullable = false)
    @NotBlank(message = "Ingresa un texto valido")
    private String descripcion;


    @Column(nullable = false)
    @NotBlank(message = "Ingresa un texto valido")
    private String fecha;

    @Column(nullable = false)
    @NotBlank(message = "Ingresa un texto valido")
    private String estado;

}
