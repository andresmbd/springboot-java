package com.myproject.catalogo_productos.repository;
import com.myproject.catalogo_productos.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
