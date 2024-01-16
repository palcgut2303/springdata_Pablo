package com.example.springdata.services;

import com.example.springdata.entity.Productos;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Productos> findAll();
    Optional<Productos> findById(Long id);
    Productos save (Productos product);

    Optional <Productos> update(Long id, Productos product);
    Optional<Productos> delete(Long id);

   // List<Productos> findAllByNombreProducto();
    //Optional<Productos> findProductosByPrecioBetween(float precionMin, float precioMax);

   // Optional<Productos> findProductosByCategoria(String categoria);
}
