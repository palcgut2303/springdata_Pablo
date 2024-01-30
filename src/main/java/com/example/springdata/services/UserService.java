package com.example.springdata.services;

import com.example.springdata.entity.Productos;
import com.example.springdata.entity.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Usuario save (Usuario usuario);
    Optional <Usuario> update(Long id, Usuario usuario);
    Optional<Usuario> delete(Long id);

    /*Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByNombreAndAndApellidos(String nombre, String apellidos);
    Optional<Usuario> findAllByFechaNacimientoAfter(LocalDate fechaNacimiento);
    Optional<Usuario> findAllByFechaNacimientoBetween(LocalDate fechaMin, LocalDate fechaMax);*/

}
