package com.example.springdata.Repositories;

import com.example.springdata.entity.Pedidos;
import com.example.springdata.entity.PedidosProductos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoPedidoRepository extends JpaRepository<PedidosProductos,Long> {
}
