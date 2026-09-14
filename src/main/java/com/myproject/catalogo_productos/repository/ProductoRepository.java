package com.myproject.catalogo_productos.repository;
import com.myproject.catalogo_productos.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
