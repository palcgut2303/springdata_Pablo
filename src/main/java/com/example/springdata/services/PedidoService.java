package com.example.springdata.services;

import com.example.springdata.entity.Pedidos;
import com.example.springdata.entity.Productos;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    List<Pedidos> findAll();
    Optional<Pedidos> findById(Long id);
    Pedidos save (Pedidos pedidos);

    Optional <Pedidos> update(Long id, Pedidos pedidos);
    Optional<Pedidos> delete(Long id);


}
