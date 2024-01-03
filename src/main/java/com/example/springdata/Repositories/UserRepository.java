package com.example.springdata.Repositories;

import com.example.springdata.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario,Long> {
    @Override
    Optional<Usuario> findById(Long aLong);
    List<Usuario> findByNombre(String nombre);
    List<Usuario> findByNombreAndAndApellidos(String nombre, String apellidos);
    List<Usuario> findAllByFechaNacimientoAfter(LocalDate fechaNacimiento);
    List<Usuario> findAllByFechaNacimientoBetween(LocalDate fechaMin, LocalDate fechaMax);
}
