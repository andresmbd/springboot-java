package com.myproject.catalogo_productos.controller;

import com.myproject.catalogo_productos.entity.Producto;
import com.myproject.catalogo_productos.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crearProducto(@RequestBody Producto producto){
        return productoService.crearProducto(producto);
    }

    @GetMapping
    public List<Producto> obtenerProductos(){
        return productoService.obtenerProductos();
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id,@RequestBody Producto producto){
        return productoService.actualizarProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
    }
}
