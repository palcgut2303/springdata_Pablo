package com.example.springdata.Repositories;

import com.example.springdata.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Productos,Long> {

    @Override
    Optional<Productos> findById(Long aLong);

    List<Productos> findAllByNombreProducto();
    List<Productos> findProductosByPrecioBetween(float precionMin, float precioMax);
    Optional<Productos> findByNombreProducto(String nombreProducto);

    List<Productos> findProductosByCategoria(String categoria);

}
