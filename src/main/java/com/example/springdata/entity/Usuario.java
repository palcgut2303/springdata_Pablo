package com.example.springdata.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dni",unique = true)
    private String DNI;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "fechNacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "tipoUsuario",nullable = false)
    private tipoUsuario tipoUsuarioo;

    @OneToMany(mappedBy = "usuario")
    private ArrayList<Pedidos> pedidos;
}
