package com.myproject.catalogo_productos.service;
import com.myproject.catalogo_productos.dto.CategoriaRequest;
import com.myproject.catalogo_productos.dto.ProductoRequest;
import com.myproject.catalogo_productos.dto.ProductoResponse;
import com.myproject.catalogo_productos.entity.Categoria;
import com.myproject.catalogo_productos.entity.Producto;
import com.myproject.catalogo_productos.exception.EntityNotFoundExeption;
import com.myproject.catalogo_productos.exception.InvalidDataException;
import com.myproject.catalogo_productos.repository.CategoriaRepository;
import com.myproject.catalogo_productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepo;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoria){
        this.productoRepository = productoRepository;
        this.categoriaRepo = categoria;
    }

    private ProductoRequest validarProducto(ProductoRequest request){
        if(request.nombre() == null || request.nombre().trim().isBlank())
            throw new InvalidDataException("El nombre es obligatorio");
        if(request.descripcion() == null || request.descripcion().trim().isBlank())
            throw new InvalidDataException("Debes colocar una descripcion del producto");
        if(request.precio() == null ||request.precio() <= 0)
            throw new InvalidDataException("Insertar un valor decimal para el precio mayor a 0");
        if (request.stock() == null || request.stock() < 5)
            throw new InvalidDataException("El stock inicial debe ser al menos 5");

        return request;
    }

    public ProductoResponse crearProducto(ProductoRequest request){
        ProductoRequest productoValidado = validarProducto(request);

        Producto producto = new Producto();
        producto.setNombre(productoValidado.nombre());
        producto.setDescripcion((productoValidado.descripcion()));
        producto.setPrecio(productoValidado.precio());
        producto.setStock(productoValidado.stock());

        Categoria categoriaExistente = categoriaRepo.findById(productoValidado.categoriaId())
                .orElseThrow(()-> new EntityNotFoundExeption("El id del producto "+productoValidado.categoriaId()+" no existe"));

        producto.setCategoria(categoriaExistente);

        Producto productoreturn =  productoRepository.save(producto);

        return new ProductoResponse(
                productoreturn.getId(),
                productoreturn.getNombre(),
                productoreturn.getDescripcion(),
                productoreturn.getPrecio(),
                productoreturn.getStock()
        );
    }

    public List<ProductoResponse> obtenerProductos(){

        List<Producto> productos = productoRepository.findAll();
        if(productos.isEmpty())
            throw new EntityNotFoundExeption("La lista esta vacia");

        return productos.stream()
                .map(producto -> new ProductoResponse(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getDescripcion(),
                        producto.getPrecio(),
                        producto.getStock()))
                .collect(Collectors.toList());
    }

    public ProductoResponse actualizarProducto(Long id, ProductoRequest nuevoProducto){
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExeption("El id del producto "+id+" no existe"));

        ProductoRequest productoValidado = validarProducto(nuevoProducto);

        productoExistente.setNombre(productoValidado.nombre());
        productoExistente.setDescripcion(productoValidado.descripcion());
        productoExistente.setPrecio(productoValidado.precio());
        productoExistente.setStock(productoValidado.stock());
        Categoria categoriaExistente = categoriaRepo.findById(productoValidado.categoriaId())
                .orElseThrow(()-> new EntityNotFoundExeption("El id del producto "+productoValidado.categoriaId()+" no existe"));
        productoExistente.setCategoria(categoriaExistente);

        Producto productoActualizado = productoRepository.save(productoExistente);

        return new ProductoResponse(
                productoActualizado.getId(),
                productoActualizado.getNombre(),
                productoActualizado.getDescripcion(),
                productoActualizado.getPrecio(),
                productoActualizado.getStock());
    }

    public void eliminarProducto(Long id){
        if(!productoRepository.existsById(id))
            throw new InvalidDataException("El producto de id: "+id+" no existe");
        productoRepository.deleteById(id);
    }
}
