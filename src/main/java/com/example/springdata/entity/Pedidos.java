package com.example.springdata.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod_pedido;

    @Column(name = "direccion_envio",nullable = false)
    @NotBlank(message = "{NotBlank.pedidos.direccionEnvio}")
    @Size(min = 5)
    private String direccionEnvio;

    @Column(name = "estadoPedido",nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private estadoPedido estadoPedido;

    @Column(name = "fecha_entrega")
    @NotNull
    private LocalDate fechaEntrega;

    @Column(name = "cantidad")
    @Max(100)
    private int cantidadProductos;

    @ManyToOne
    @JoinColumn(name = "id")
    @JsonBackReference
    private Usuario usuario;


    @ManyToMany
    @JoinTable(name = "pedidos_productos",
                joinColumns = @JoinColumn(name = "cod_pedido",referencedColumnName = "cod_pedido"),
                inverseJoinColumns = @JoinColumn(name = "cod_producto",referencedColumnName = "cod_producto"))
    private Set<Productos> productos = new HashSet<>();
}
