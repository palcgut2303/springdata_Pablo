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
    Optional<Usuario> findByUsername(String nombre);

    List<Usuario> findAllByNombre(String nombre);
    List<Usuario> findAllByApellidos(String apellidos);
    List<Usuario> findAllByEdadBetween(int edadMin,int edadMax);

}
