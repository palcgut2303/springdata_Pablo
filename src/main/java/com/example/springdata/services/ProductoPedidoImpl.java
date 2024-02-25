package com.example.springdata.services;

import com.example.springdata.Repositories.PedidoRepository;
import com.example.springdata.Repositories.ProductoPedidoRepository;
import com.example.springdata.entity.Pedidos;
import com.example.springdata.entity.PedidosProductos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class ProductoPedidoImpl implements ProductoPedidoService{

    @Autowired
    private ProductoPedidoRepository productoPedidoRepository;

    @Override
    public List<PedidosProductos> findAll() {
        return productoPedidoRepository.findAll();
    }

    @Override
    public Optional<PedidosProductos> findById(Long id) {
        return productoPedidoRepository.findById(id);
    }

    @Override
    public PedidosProductos save(PedidosProductos pedidosProducto) {
        return productoPedidoRepository.save(pedidosProducto);
    }

    @Override
    public Optional<PedidosProductos> update(Long id, PedidosProductos pedidos) {
        Optional <PedidosProductos> pedidoProductoOptional = productoPedidoRepository.findById(id);
        if(pedidoProductoOptional.isPresent()){
            PedidosProductos pedidoProductoDB = pedidoProductoOptional.orElseThrow();
            pedidoProductoDB.setCantidadProductos(pedidos.getCantidadProductos());
            pedidoProductoDB.setProducto(pedidos.getProducto());
            pedidoProductoDB.setPedido(pedidos.getPedido());
            return Optional.of(productoPedidoRepository.save(pedidoProductoDB));
        }

        return Optional.empty();
    }

    @Override
    public Optional<PedidosProductos> delete(Long id) {
        Optional <PedidosProductos> pedidoProductoOptional = productoPedidoRepository.findById(id);
        pedidoProductoOptional.ifPresent( pedidoProductDb -> productoPedidoRepository.delete(pedidoProductDb));
        return pedidoProductoOptional;
    }
}
