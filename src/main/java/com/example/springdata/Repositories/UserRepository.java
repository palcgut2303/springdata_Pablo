package com.example.springdata.Repositories;

import com.example.springdata.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario,Long> {
/*
    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByNombreAndAndApellidos(String nombre, String apellidos);
    Optional<Usuario> findAllByFechaNacimientoAfter(LocalDate fechaNacimiento);
    Optional<Usuario> findAllByFechaNacimientoBetween(LocalDate fechaMin, LocalDate fechaMax);*/
}
