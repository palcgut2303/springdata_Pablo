package com.example.springdata.controllers;


import com.example.springdata.entity.Pedidos;
import com.example.springdata.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedidos> list(){
        return pedidoService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> view(@PathVariable Long id){
        Optional<Pedidos> productOptional = pedidoService.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pedidos> create(@RequestBody @Validated Pedidos product){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedidos> update(@PathVariable Long id,@Validated @RequestBody Pedidos product){
        Optional <Pedidos> productOptional = pedidoService.update(id, product);
        if(productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pedidos> delete(@PathVariable Long id){
        Optional<Pedidos> productOptional = pedidoService.delete(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


}
