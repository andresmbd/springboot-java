package com.myproject.catalogo_productos.controller;

import com.myproject.catalogo_productos.dto.CategoriaRequest;
import com.myproject.catalogo_productos.dto.CategoriaResponse;
import com.myproject.catalogo_productos.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class
CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService= categoriaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponse crearCategoria(@RequestBody CategoriaRequest categoria){
        return categoriaService.crearCategoria(categoria);
    }

    @GetMapping// Spring responde 200 OK por defecto.
    public List<CategoriaResponse> obtenerCategorias(){

        return categoriaService.obtenerCategorias();
    }

    @PutMapping("/{id}")
    public CategoriaResponse actualizarCategoria(@PathVariable Long id,@RequestBody CategoriaRequest categoria){
        return categoriaService.actualizarCategoria(id, categoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCategoria(@PathVariable Long id){
        categoriaService.eliminarCategoria(id);
    }

}
