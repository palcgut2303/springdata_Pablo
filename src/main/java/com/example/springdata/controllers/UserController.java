package com.example.springdata.controllers;


import com.example.springdata.Repositories.UserRepository;
import com.example.springdata.entity.Usuario;
import com.example.springdata.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userRepository;

    @GetMapping
    public List<Usuario> list(){
        return userRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> view(@PathVariable Long id){
        Optional<Usuario> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            return ResponseEntity.ok(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Usuario user, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody Usuario user, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional <Usuario> userOptional = userRepository.update(id, user);
        if(userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable Long id){
        Optional<Usuario> userOptional = userRepository.delete(id);
        if(userOptional.isPresent()){
            return ResponseEntity.ok(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors = new HashMap<>();

        result.getFieldErrors().forEach(fieldError ->{
            errors.put(fieldError.getField(),"El campo "+fieldError.getField()+" "+fieldError.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);

    }

}
