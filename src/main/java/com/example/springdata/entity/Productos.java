package com.example.springdata.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "producto")
    private ArrayList<PedidoProducto> pedidosEnPedidos = new ArrayList<>();

}
