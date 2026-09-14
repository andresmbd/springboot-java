package com.myproject.catalogo_productos.service;

import com.myproject.catalogo_productos.entity.Categoria;
import com.myproject.catalogo_productos.exception.InvalidDataException;
import com.myproject.catalogo_productos.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

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

    public Categoria crearCategoria(Categoria categoria){
        if (categoria.getNombre() == null || categoria.getNombre().trim().isBlank())
            throw new InvalidDataException("El nombre es obligatorio");
        // si se hace un throw no hay necesidad de un else if
        if (categoria.getDescripcion() == null || categoria.getDescripcion().trim().isBlank())
            throw new InvalidDataException("Debe llenar el campo de la descripcion");

        return categoriaRepository.save(categoria);
    }

}
