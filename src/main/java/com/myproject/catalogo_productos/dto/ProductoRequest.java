package com.myproject.catalogo_productos.dto;

import com.myproject.catalogo_productos.entity.Categoria;

public record ProductoRequest(
        String nombre,
        String descripcion,
        Double precio,
        Integer stock,
        Long categoriaId
) {
}
