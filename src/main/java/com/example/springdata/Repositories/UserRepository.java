package com.example.springdata.Repositories;

import com.example.springdata.entity.Usuario;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByUsername(String username);

    List<Usuario> findAllByApellidos(String apellido);

    @Query("select u from Usuario u where u.nombre like %:cadenaNombre% ")
    List<Usuario> findAllByNombre(String cadenaNombre);

    @Query("select u from Usuario u where u.Edad BETWEEN :edadMin and :edadMax")
    List<Usuario> findAllByEdadBetween(int edadMin,int edadMax);
}
