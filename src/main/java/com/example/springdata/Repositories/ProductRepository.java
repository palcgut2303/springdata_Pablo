package com.example.springdata.Repositories;

import com.example.springdata.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Productos,Long> {

    List<Productos> findAllByNombreProducto(String nombre);
    List<Productos> findProductosByPrecioBetween(float precionMin, float precioMax);
    List<Productos> findProductosByCategoria(String categoria);

}
