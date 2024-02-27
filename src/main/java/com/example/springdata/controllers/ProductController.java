package com.example.springdata.controllers;

import com.example.springdata.entity.Pedidos;
import com.example.springdata.entity.Productos;
import com.example.springdata.services.ProductService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Productos",description = "Controlador Productos, metodos CRUD y consultas propias")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de Productos.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Productos.class)))})
    })
    @Operation(summary = "Listado de productos",description = "Devuelve una lista de productos que este en la base de datos.")
    public List<Productos> list() {return productService.findAll();}

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Listado de un producto por su Id.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Productos.class)))})
    })
    @Operation(summary = "Listado de un producto por Id",description = "Devuelve un producto.")
    public ResponseEntity<Productos> view(@PathVariable Long id){
        Optional<Productos> productOptional = productService.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de Productos por nombre del producto introducido.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Productos.class)))})
    })
    @Operation(summary = "Lista de Productos por nombre del producto introducido.",description = "Devuelve una lista de productos que este en la base de datos, especificado por el nombre introducido.")
    public List<Productos> obtenerProductoPorNombre(@RequestParam("nombre") String nombre){
       return productService.findAllByNombreProducto(nombre);
    }

    @GetMapping("/categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de Productos por categoria introducida",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Productos.class)))})
    })
    @Operation(summary = "Lista de Productos por categoria introducida.",description = "Devuelve una lista de productos segun la categoria que introduciremos.")
    public List<Productos> obtenerProductoPorCategoria(@RequestParam("categoria") String categoria){
        return productService.findProductosByCategoria(categoria);
    }

    @GetMapping("/precio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de Productos por rando de precio introducido.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Productos.class)))})
    })
    @Operation(summary = "Lista de Productos por rango de precio introducido.",description = "Devuelve una lista de productos, segun el rango de precios.")
    public List<Productos> obtenerProductoPorPrecio(@RequestParam("precioMin") float precioMin, @RequestParam("precioMax") float precioMax){
        return productService.findProductosByPrecioBetween(precioMin,precioMax);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Producto Insertado.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Productos.class)))})
    })
    @Operation(summary = "Insertar Productos.",description = "Insertar productos en la base de datos")
    public ResponseEntity<?> create( @Valid @RequestBody Productos product, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Producto Actualizado.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Productos.class)))})
    })
    @Operation(summary = "Actualizar producto.",description = "Actualiza un producto de la base de datos.")
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Producto Borrado.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Productos.class)))})
    })
    @Operation(summary = "Borra un producto.",description = "Borra un producto de la base de datos.")
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
