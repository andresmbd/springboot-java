package com.myproject.catalogo_productos.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // indica que la clase es una entidad.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;
}
