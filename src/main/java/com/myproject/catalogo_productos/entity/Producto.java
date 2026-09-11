package com.myproject.catalogo_productos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import  lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer stock;

    /**
     * JPA es quien hace esa conversión
     * entre el mundo de objetos Java y
     * el mundo de tablas de la base de
     * datos.
     *
     * En la base de datos relacional,
     * la relación normalmente se
     * representa mediante una clave
     * foránea: categoria_id
     *
     * categoria es una referencia a un objeto Categoria en Java.
     * JPA utiliza esa relación para representar una clave foránea
     * (categoria_id) en la base de datos.
     */

    @ManyToOne(optional = false) // Representa la relacion entre Categoria y Producto N:1
    private Categoria categoria;
}
