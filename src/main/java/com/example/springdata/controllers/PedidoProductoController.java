package com.example.springdata.controllers;

import com.example.springdata.entity.Pedidos;
import com.example.springdata.entity.PedidosProductos;
import com.example.springdata.services.PedidoService;
import com.example.springdata.services.ProductoPedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidosProductos")
public class PedidoProductoController {

    @Autowired
    private ProductoPedidoService pedidoProductoService;

    @GetMapping
    public List<PedidosProductos> list(){
        return pedidoProductoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidosProductos> view(@PathVariable Long id){
        Optional<PedidosProductos> pedidoProductoOptional = pedidoProductoService.findById(id);
        if(pedidoProductoOptional.isPresent()){
            return ResponseEntity.ok(pedidoProductoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PedidosProductos pedidosProductos, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoProductoService.save(pedidosProductos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PedidosProductos pedidosProductos, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional <PedidosProductos> pedidosProductosOptional = pedidoProductoService.update(id, pedidosProductos);
        if(pedidosProductosOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidosProductosOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PedidosProductos> delete(@PathVariable Long id){
        Optional<PedidosProductos> pedidosProductosOptional = pedidoProductoService.delete(id);
        if(pedidosProductosOptional.isPresent()){
            return ResponseEntity.ok(pedidosProductosOptional.orElseThrow());
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
