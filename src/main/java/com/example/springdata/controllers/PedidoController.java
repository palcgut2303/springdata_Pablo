package com.example.springdata.controllers;


import com.example.springdata.entity.Pedidos;
import com.example.springdata.entity.Usuario;
import com.example.springdata.services.PedidoService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name="Pedidos",description = "Controlador Pedidos, metodos CRUD y consultas propias")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de pedidos.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Pedidos.class)))})
    })
    @Operation(summary = "Listado de pedidos",description = "Devuelve una lista de pedidos que este en la base de datos.")
    public List<Pedidos> list(){
        return pedidoService.findAll();
    }
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Pedidos encontrados por la id.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Pedidos.class)))})
    })
    @Operation(summary = "Listado de pedido por id",description = "Devuelve un pedido que este en la base de datos por su id.")
    public ResponseEntity<Pedidos> view(@PathVariable Long id){
        Optional<Pedidos> productOptional = pedidoService.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/direccion/{direccion}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Pedidos por direccion introducida.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Pedidos.class)))})
    })
    @Operation(summary = "Pedidos por direccion",description = "Devuelve una lista de pedidos que coincida con la direccion introducida.")
    public List<Pedidos> obtenerPedidosPorDireccionEnvio(@PathVariable String direccion){
        return pedidoService.findAllByDireccion(direccion);

    }

    @GetMapping("/usuario/{usuarioId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de pedidos por usuario.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Pedidos.class)))})
    })
    @Operation(summary = "Listado de pedidos de un usuario concreto",description = "Devuelve una lista de pedidos por usuario registrado en la base de datos.")
    public List<Pedidos> obtenerPedidosPorUsuario(@PathVariable Long usuarioId) {
        return pedidoService.findAllByUsuarioId(usuarioId);

    }

    @GetMapping("/fecha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Lista de pedidos entre dos fechas",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Pedidos.class)))})
    })
    @Operation(summary = "Listado de pedidos entre dos fechas",description = "Devuelve una lista de pedidos en la que su fecha coincide entre las fechas introducidas.")
    public List<Pedidos> obtenerPedidosEntreFechas(
            @RequestParam("fechaInicio") LocalDate fechaInicio,
            @RequestParam("fechaFin") LocalDate fechaFin) {
        return pedidoService.findAllByFechaEntregaBetween(fechaInicio,fechaFin);

    }


    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Inserta Pedidos.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Pedidos.class)))})
    })
    @Operation(summary = "Insert de Pedidos",description = "Inserta pedidos en la base de datos.")
    public ResponseEntity<?> create(@Valid @RequestBody Pedidos product, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(product));
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Pedido actualizado.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Pedidos.class)))})
    })
    @Operation(summary = "Actualizacion de pedidos",description = "Actualizar un pedidos de la base de datos cuyo id sea el que introduzcamos.")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Pedidos product, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional <Pedidos> productOptional = pedidoService.update(id, product);
        if(productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Pedido Borrado.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Pedidos.class)))})
    })
    @Operation(summary = "Borrado de pedido",description = "Borrar un pedido que se encuentra en la base de datos.")
    public ResponseEntity<Pedidos> delete(@PathVariable Long id){
        Optional<Pedidos> productOptional = pedidoService.delete(id);
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
