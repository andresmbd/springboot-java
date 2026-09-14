package com.myproject.catalogo_productos.service;
import com.myproject.catalogo_productos.entity.Producto;
import com.myproject.catalogo_productos.exception.InvalidDataException;
import com.myproject.catalogo_productos.repository.CategoriaRepository;
import com.myproject.catalogo_productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepo;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoria){
        this.productoRepository = productoRepository;
        this.categoriaRepo = categoria;
    }

    public Producto crearProducto(Producto producto){

        if(producto.getNombre() == null || producto.getNombre().trim().isBlank())
            throw new InvalidDataException("El nombre es obligatorio");
        if(producto.getDescripcion() == null || producto.getDescripcion().trim().isBlank())
            throw new InvalidDataException("Debes colocar una descripcion del producto");
        if(producto.getPrecio() == null ||producto.getPrecio() <= 0)
            throw new InvalidDataException("Insertar un valor decimal para el precio mayor a 0");
        if (producto.getStock() == null || producto.getStock() < 5)
            throw new InvalidDataException("El stock inicial debe ser al menos 5");
        if (producto.getCategoria() == null || !categoriaRepo.existsById(producto.getCategoria().getId()))
            throw new InvalidDataException("Inserta una categoria real para el producto");
        return productoRepository.save(producto);
    }
}
