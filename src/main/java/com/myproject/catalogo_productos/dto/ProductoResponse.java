package com.myproject.catalogo_productos.dto;

public record ProductoResponse(
        Long id,
        String nombre,
        String descripcion,
        Double precio,
        Integer stock
) {
}