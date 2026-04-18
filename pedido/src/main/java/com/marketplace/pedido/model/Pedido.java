package com.marketplace.pedido.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "La fecha es obligatoria")
    @Column(nullable = false)
    private String fecha;

    @NotNull(message = "El total es obligatorio")
    @Column(nullable = false)
    private int total;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false)
    private String estado;
}
