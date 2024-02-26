package com.example.springdata.services;

import com.example.springdata.Repositories.RoleRepository;
import com.example.springdata.Repositories.UserRepository;
import com.example.springdata.entity.Role;
import com.example.springdata.entity.Usuario;
import com.example.springdata.security.SpringSecurityConfig;
import org.apache.catalina.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
     UserRepository userRepository;

    @Autowired
     RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<Role> rolesList = new ArrayList<>();
        optionalRoleUser.ifPresent(rolesList::add);

        if(usuario.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(rolesList::add);
        }

        usuario.setRoles(rolesList);

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));


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

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAllByNombre(String nombre) {
        return userRepository.findAllByNombre(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAllByApellidos(String apellidos) {
        return userRepository.findAllByApellidos(apellidos);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAllByEdadBetween(int edadMin,int edadMax) {
        return userRepository.findAllByEdadBetween(edadMin,edadMax);
    }

}
