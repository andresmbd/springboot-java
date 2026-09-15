package com.myproject.catalogo_productos.controller;

import com.myproject.catalogo_productos.entity.Categoria;
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
    public Categoria crearCategoria(@RequestBody Categoria categoria){
        return categoriaService.crearCategoria(categoria);
    }

    @GetMapping// Spring responde 200 OK por defecto.
    public List<Categoria> obtenerCategorias(){
        return categoriaService.obtenerCategorias();
    }

    @PutMapping("/{id}")
    public Categoria actualizarCategoria(@PathVariable Long id,@RequestBody Categoria categoria){
        return categoriaService.actualizarCategoria(id, categoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCategoria(@PathVariable Long id){
        categoriaService.eliminarCategoria(id);
    }

}
