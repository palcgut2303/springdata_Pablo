package com.example.springdata.services;

import com.example.springdata.Repositories.UserRepository;
import com.example.springdata.entity.Usuario;
import org.apache.catalina.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return userRepository.save(usuario);
    }

    @Override
    @Transactional
    public Optional<Usuario> update(Long id, Usuario usuario) {
        Optional<Usuario> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            Usuario userDB = userOptional.orElseThrow();
            userDB.setNombre(usuario.getNombre());
            userDB.setApellidos(usuario.getApellidos());
            userDB.setDNI(usuario.getDNI());
            userDB.setCorreo(usuario.getCorreo());
            userDB.setPedidos(usuario.getPedidos());
            userDB.setEdad(usuario.getEdad());
            userDB.setTipoUsuarioo(usuario.getTipoUsuarioo());
            return Optional.of(userRepository.save(userDB));
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> delete(Long id) {
        Optional<Usuario> userOptional = userRepository.findById(id);
        userOptional.ifPresent(userDB -> userRepository.delete(userDB));
        return userOptional;
    }

  /*  @Override
    @Transactional
    public Optional<Usuario> findByNombre(String nombre) {
        return userRepository.findByNombre(nombre);
    }

    @Override
    @Transactional
    public Optional<Usuario> findByNombreAndAndApellidos(String nombre, String apellidos) {
        return userRepository.findByNombreAndAndApellidos(nombre,apellidos);
    }

    @Override
    @Transactional
    public Optional<Usuario> findAllByFechaNacimientoAfter(LocalDate fechaNacimiento) {
        return userRepository.findAllByFechaNacimientoAfter(fechaNacimiento);
    }

    @Override
    @Transactional
    public Optional<Usuario> findAllByFechaNacimientoBetween(LocalDate fechaMin, LocalDate fechaMax) {
        return userRepository.findAllByFechaNacimientoBetween(fechaMin,fechaMax);
    }*/
}
