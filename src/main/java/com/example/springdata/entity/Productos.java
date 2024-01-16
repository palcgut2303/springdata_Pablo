package com.example.springdata.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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

    @Column(name = "nombre_producto",nullable = false)
    private String nombreProducto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio",nullable = false)
    private float precio;

    @Column(name = "cantidad_en_stock",nullable = false)
    private int cantidadEnStock;

    @Column(name = "categoria",nullable = false)
    private String categoria;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(name = "pedidos_productos",
            joinColumns = @JoinColumn(name = "cod_producto",referencedColumnName = "cod_producto"),
            inverseJoinColumns = @JoinColumn(name = "cod_pedido",referencedColumnName = "cod_pedido"))
    private Set<Pedidos> pedidos = new HashSet<>();

}
