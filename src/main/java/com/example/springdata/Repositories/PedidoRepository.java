package com.example.springdata.Repositories;

import com.example.springdata.entity.Pedidos;
import com.example.springdata.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedidos,Long> {

    List<Pedidos> findAllByDireccionEnvio(String direccion);

    List<Pedidos> findAllByUsuarioId(Long id);

    List<Pedidos> findAllByFechaEntregaBetween(LocalDate fechaIn, LocalDate fechaFin);



}
