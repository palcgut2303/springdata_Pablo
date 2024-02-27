package com.example.springdata.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1",description = "Codigo Identificador del usuario, autoincremental")
    private Long id;

    @Column(unique = true)
    @NotBlank
    @Size(min = 4,max = 16)
    @Schema(example = "palcgut",description = "Nombre de usuario que se utiliza para el login")
    private String username;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(example = "123", description = "Contraseña que nos servira para el login al igual que el usuario")
    private String password;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","role_id"})}
    )
    private List<Role> roles;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;

    @Column(name = "dni",unique = true)
    @Size(min = 2,max = 9)
    @NotNull
    @Schema(example = "12345678A", description = "DNI del usuario, campo unico, no se puede repetir")
    private String DNI;

    @Email(message = "{Email.usuario.correo}")
    @NotNull
    @Schema(example = "pablo@gmail.com", description = "Email del usuario, tiene que tener un formato email correcto.")
    private String correo;

    @Column(name = "nombre")
    @Size(min = 4,max = 15)
    @NotEmpty(message = "{NotEmpty.usuario.nombre}")
    @Schema(example = "Pablo", description = "Nombre del usuario, tamaño maximo 15 y minimo 4 caracteres")
    private String nombre;

    @Column(name = "apellidos")
    @Size(min = 2,max = 100)
    @Schema(example = "Alcudia", description = "Apellidos del usuario, tamaño maximo 100 y minimo 2 caracteres")
    private String apellidos;

    @Column(name = "Edad")
    @Min(18)
    @Schema(example = "23", description = "Edad del usuario solo puede ser mayor de 18 años")
    private int Edad;

    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Pedidos> pedidos;

    private boolean enabled;
    @PrePersist
    public void prePresit(){
        enabled = true;
    }
}
