package com.example.springdata.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
    private String direccionEnvio;

    @Column(name = "estadoPedido",nullable = false)
    @Enumerated(EnumType.STRING)
    private estadoPedido estadoPedido;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @Column(name = "cantidad")
    private int cantidadProductos;

    @ManyToOne
    @JoinColumn(name = "id")
   // @JsonBackReference
    private Usuario usuario;


    @ManyToMany(cascade = CascadeType.ALL)

    @JoinTable(name = "pedidos_productos",
                joinColumns = @JoinColumn(name = "cod_pedido",referencedColumnName = "cod_pedido"),
                inverseJoinColumns = @JoinColumn(name = "cod_producto",referencedColumnName = "cod_producto"))
    private Set<Productos> productos = new HashSet<>();
}
