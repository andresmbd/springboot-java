package com.myproject.catalogo_productos.controller;

import com.myproject.catalogo_productos.entity.Categoria;
import com.myproject.catalogo_productos.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService= categoriaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria crearCategoria(@RequestBody Categoria categoria){
        return categoriaService.crearCategoria(categoria);
    }
}
