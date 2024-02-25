package com.example.springdata.controllers;

import com.example.springdata.entity.Productos;
import com.example.springdata.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Productos> list() {return productService.findAll();}
    @GetMapping("/{id}")
    public ResponseEntity<Productos> view(@PathVariable Long id){
        Optional<Productos> productOptional = productService.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nombre")
    public List<Productos> obtenerProductoPorNombre(@RequestParam("nombre") String nombre){
       return productService.findAllByNombreProducto(nombre);
    }

    @GetMapping("/categoria")
    public List<Productos> obtenerProductoPorCategoria(@RequestParam("categoria") String categoria){
        return productService.findProductosByCategoria(categoria);
    }

    @GetMapping("/precio")
    public List<Productos> obtenerProductoPorPrecio(@RequestParam("precioMin") float precioMin, @RequestParam("precioMax") float precioMax){
        return productService.findProductosByPrecioBetween(precioMin,precioMax);
    }

    @PostMapping
    public ResponseEntity<?> create( @Valid @RequestBody Productos product, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody Productos product, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional <Productos> productOptional = productService.update(id, product);
        if(productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Productos> delete(@PathVariable Long id){
        Optional<Productos> productOptional = productService.delete(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
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
