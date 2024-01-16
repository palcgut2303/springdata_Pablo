package com.example.springdata.Repositories;

import com.example.springdata.entity.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedidos,Long> {

/*
    Optional<Pedidos> findByDireccionEnvio(String direccion);*/
}
