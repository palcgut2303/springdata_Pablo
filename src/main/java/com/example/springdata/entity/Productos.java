package com.example.springdata.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod_producto;

    @Column(name = "nombre_producto")
    @NotNull(message = "{NotNull.productos}")
    private String nombreProducto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    @NotNull(message = "{NotNull.productos}")
    private float precio;

    @Column(name = "cantidad_en_stock")
    @NotNull(message = "{NotNull.productos}")
    private int cantidadEnStock;

    @Column(name = "categoria")
    @NotNull(message = "{NotNull.productos}")
    private String categoria;

    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "pedidos_productos",
            joinColumns = @JoinColumn(name = "cod_producto",referencedColumnName = "cod_producto"),
            inverseJoinColumns = @JoinColumn(name = "cod_pedido",referencedColumnName = "cod_pedido"))
    private Set<Pedidos> pedidos = new HashSet<>();

}
