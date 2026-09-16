package com.myproject.catalogo_productos.service;

import com.myproject.catalogo_productos.dto.CategoriaRequest;
import com.myproject.catalogo_productos.dto.CategoriaResponse;
import com.myproject.catalogo_productos.entity.Categoria;
import com.myproject.catalogo_productos.exception.InvalidDataException;
import com.myproject.catalogo_productos.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    /**
     * 1. Cuando tu aplicación arranca, Spring escanea tu
     * código buscando interfaces que extiendan de JpaRepository.
     *
     * 2. Al encontrar CategoriaRepository, Spring utiliza una
     * tecnología de Java llamada Dynamic Proxies (o librerías
     * como ByteBuddy). Con esto, genera automáticamente en la
     * memoria de la computadora una clase oculta que sí implementa
     * tu interfaz. (O sea crea una clase real en tiempo de ejecución
     * (una clase proxy))
     *
     * 3. Spring lee el nombre de los métodos (como findAll o findById) y
     * genera el código
     * SQL real correspondiente para tu base de datos.
     *
     * 4. Spring toma una instancia (un objeto) de esa clase oculta que acaba de
     * inventar y la introduce en tu variable @Autowired categoriaRepository.
     */
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaResponse crearCategoria(CategoriaRequest request){
        if (request.nombre() == null || request.nombre().trim().isBlank())
            throw new InvalidDataException("El nombre es obligatorio");
        // si se hace un throw no hay necesidad de un else if
        if (request.descripcion() == null || request.descripcion().trim().isBlank())
            throw new InvalidDataException("Debe llenar el campo de la descripcion");

        Categoria categoria = new Categoria();
        categoria.setNombre(request.nombre());
        categoria.setDescripcion(request.descripcion());

        Categoria returnCategory = categoriaRepository.save(categoria);

        return new CategoriaResponse(
                returnCategory.getId(),
                returnCategory.getNombre(),
                returnCategory.getDescripcion());
    }

    public List<Categoria> obtenerCategorias(){

        return categoriaRepository.findAll();
    }

    public Categoria actualizarCategoria(Long id, Categoria nuevaCategoria){
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(()-> new InvalidDataException("El id de la categoria "+id+" no existe"));

        categoriaExistente.setNombre(nuevaCategoria.getNombre());
        categoriaExistente.setDescripcion(nuevaCategoria.getDescripcion());

        return crearCategoria(categoriaExistente);
    }

    public void eliminarCategoria(Long id){
        if(!categoriaRepository.existsById(id))
            throw new InvalidDataException("La categoria de id: "+id+" no existe");
        categoriaRepository.deleteById(id);
    }

}
