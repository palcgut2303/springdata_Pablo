package com.example.springdata.entity;

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
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","role_id"})}
    )
    private List<Role> roles;

    @Transient
    private boolean admin;

    @Column(name = "dni",unique = true)
    @Size(min = 2,max = 9)
    @NotNull
    private String DNI;

    @Email
    private String correo;

    @Column(name = "nombre")
    @Size(min = 4,max = 15)
    @NotEmpty(message = "{NotEmpty.usuario.nombre}")
    private String nombre;

    @Column(name = "apellidos")
    @Size(min = 2,max = 100)
    private String apellidos;

    @Column(name = "Edad")
    @Min(18)
    private int Edad;

    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
    private Set<Pedidos> pedidos = new HashSet<>();
}
