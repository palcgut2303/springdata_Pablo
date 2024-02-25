package com.example.springdata.services;

import com.example.springdata.entity.Pedidos;
import com.example.springdata.entity.PedidosProductos;

import java.util.List;
import java.util.Optional;

public interface ProductoPedidoService {

    List<PedidosProductos> findAll();
    Optional<PedidosProductos> findById(Long id);
    PedidosProductos save (PedidosProductos pedidos);

    Optional <PedidosProductos> update(Long id, PedidosProductos pedidos);
    Optional<PedidosProductos> delete(Long id);
}
