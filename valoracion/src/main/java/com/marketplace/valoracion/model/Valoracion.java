package com.marketplace.valoracion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "valoracion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Valoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
