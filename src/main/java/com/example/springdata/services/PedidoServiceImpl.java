package com.example.springdata.services;

import com.example.springdata.Repositories.PedidoRepository;
import com.example.springdata.entity.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Pedidos> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pedidos> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    @Transactional
    public Pedidos save(Pedidos pedidos) {
        return pedidoRepository.save(pedidos);
    }

    @Override
    @Transactional
    public Optional<Pedidos> update(Long id, Pedidos pedidos) {
        Optional <Pedidos> pedidoOptional = pedidoRepository.findById(id);
        if(pedidoOptional.isPresent()){
            Pedidos pedidoDB = pedidoOptional.orElseThrow();
            pedidoDB.setDireccionEnvio(pedidos.getDireccionEnvio());
            pedidoDB.setUsuario(pedidos.getUsuario());
            pedidoDB.setProductosEnPedidos(pedidos.getProductosEnPedidos());
            return Optional.of(pedidoRepository.save(pedidoDB));
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Pedidos> delete(Long id) {
        Optional <Pedidos> peididoOptional = pedidoRepository.findById(id);
        peididoOptional.ifPresent( pedidoDb -> pedidoRepository.delete(pedidoDb));
        return peididoOptional;
    }
  /*  @Override
    @Transactional
    public Optional<Pedidos> findByDireccionEnvio(String direccion){
        return pedidoRepository.findByDireccionEnvio(direccion);
    }*/
}
