package com.myproject.catalogo_productos.dto;

public record CategoriaResponse(
        Long id,
        String nombre,
        String descripcion
) {
}