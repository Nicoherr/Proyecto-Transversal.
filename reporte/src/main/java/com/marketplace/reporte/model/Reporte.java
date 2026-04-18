package com.marketplace.reporte.model;

import jakarta.persistence.*;
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
    private String concepto;


    @Column(nullable = false)
    private String valor;


    @Column(nullable = false)
    private String fecha;
}
