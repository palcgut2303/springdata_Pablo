package com.example.springdata.Repositories;

import com.example.springdata.entity.Pedidos;
import com.example.springdata.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedidos,Long> {

    @Query("SELECT p FROM Pedidos p where p.direccionEnvio = ?1")
    List<Pedidos> findAllByDireccionEnvio(String direccion);

    List<Pedidos> findAllByUsuarioId(Long id);

    @Query("SELECT p FROM Pedidos p WHERE p.fechaEntrega BETWEEN :fechaIn AND :fechaFin")
    List<Pedidos> findAllByFechaEntregaBetween(LocalDate fechaIn, LocalDate fechaFin);



}
