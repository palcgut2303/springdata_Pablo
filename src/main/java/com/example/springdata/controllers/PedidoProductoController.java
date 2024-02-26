package com.example.springdata.controllers;

import com.example.springdata.entity.Pedidos;
import com.example.springdata.entity.PedidosProductos;
import com.example.springdata.services.PedidoService;
import com.example.springdata.services.ProductoPedidoService;
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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidosProductos")
@Tag(name="Pedido_Producto",description = "Controlador PedidoProducto, metodos CRUD, de la tabla generada de la N:M")
public class PedidoProductoController {

    @Autowired
    private ProductoPedidoService pedidoProductoService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de pedidos_productos.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = PedidosProductos.class)))})
    })
    @Operation(summary = "Listado de pedidos_productos",description = "Devuelve una lista de pedidos_productos que este en la base de datos.")
    public List<PedidosProductos> list(){
        return pedidoProductoService.findAll();
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de pedidos_productos por su id.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = PedidosProductos.class)))})
    })
    @Operation(summary = "Listado de un objeto pedidos_productos por su id",description = "Devuelve un objeto de pedidos_productos por su id introducida.")
    public ResponseEntity<PedidosProductos> view(@PathVariable Long id){
        Optional<PedidosProductos> pedidoProductoOptional = pedidoProductoService.findById(id);
        if(pedidoProductoOptional.isPresent()){
            return ResponseEntity.ok(pedidoProductoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Insertar un objeto de pedidos_productos.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = PedidosProductos.class)))})
    })
    @Operation(summary = "Insertar un objeto de pedidos_productos",description = "Insertar un objeto de pedidos_productos en la base de datos.")
    public ResponseEntity<?> create(@Valid @RequestBody PedidosProductos pedidosProductos, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoProductoService.save(pedidosProductos));
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Actualizar un objeto de pedidos_productos.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = PedidosProductos.class)))})
    })
    @Operation(summary = "Actualizar un objeto de pedidos_productos",description = "Actualizar un objeto de pedidos_productos en la base de datos.")
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Borrar un objeto de pedidos_productos.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = PedidosProductos.class)))})
    })
    @Operation(summary = "Borrar un objeto de pedidos_productos",description = "Borrar un objeto de pedidos_productos en la base de datos.")
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
