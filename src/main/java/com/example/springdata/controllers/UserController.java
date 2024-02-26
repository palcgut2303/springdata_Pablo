package com.example.springdata.controllers;


import com.example.springdata.entity.Usuario;
import com.example.springdata.services.UserService;
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

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Tag(name="Usuarios",description = "Controlador Usuario, metodos CRUD y consultas propias")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuarios Encontrados",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))})
    })
    @Operation(summary = "Lista de todos los usuarios",description = "Devuelve todos los usuarios de la base de datos registrados")
    public List<Usuario> list(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuario Encontrado por id",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))})
    })
    @Operation(summary = "Encuentra usuario por Id",description = "Devuelve un usuario con un id especifico que nosotros introducimos")
    public ResponseEntity<Usuario> view(@PathVariable Long id){
        Optional<Usuario> userOptional = userService.findById(id);
        if(userOptional.isPresent()){
            return ResponseEntity.ok(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuarios encontrados con la cadena.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))})
    })
    @Operation(summary = "Encuentra usuarios por cadena",description = "Devuelve una lista de usuarios o un usuario con la cadena que nosotros le especifiquemos.")
    public List<Usuario> obtenerUsuarioPorNombre(@RequestParam("cadenaNombre") String nombre){
       return userService.findAllByNombre(nombre);
    }

    @GetMapping("/apellido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuarios encontrados por apellidos.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))})
    })
    @Operation(summary = "Encuentra usuarios por Apellidos",description = "Devuelve una lista de usuarios o un usuario con el apellido que nosotros le especifiquemos.")
    public List<Usuario> obtenerUsuarioPorApellido(@RequestParam("apellido") String apellido){
        return userService.findAllByApellidos(apellido);
    }

    @GetMapping("/edad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuarios encontrados entre dos edades.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))})
    })
    @Operation(summary = "Encuentra usuarios entre dos edades",description = "Devuelve una lista de usuarios en la que su edad esta entre las indicadas.")
    public List<Usuario> obtenerUsuariosPorEdadEntre(@RequestParam("edadMin") int edadMin,@RequestParam("edadMax") int edadMax){
        return userService.findAllByEdadBetween(edadMin,edadMax);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Usuario user, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PostMapping("/register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Registra usuarios en la base de datos.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))})
    })
    @Operation(summary = "Registrar Usuario",description = "Insertar usuarios en la base de datos.")
    public ResponseEntity<?> register(@Valid @RequestBody Usuario user, BindingResult result){
        return create(user, result);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Actualizar usuarios.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))})
    })
    @Operation(summary = "Actualizacion de usuario",description = "Actualiza los usuarios registrados en la base de datos")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody Usuario user, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional <Usuario> userOptional = userService.update(id, user);
        if(userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Borrar Usuarios.",content = {
                    @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))})
    })
    @Operation(summary = "Borrar Usuarios",description = "Borra usuarios a partir de una id.")
    public ResponseEntity<Usuario> delete(@PathVariable Long id){
        Optional<Usuario> userOptional = userService.delete(id);
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
