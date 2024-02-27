package com.example.springdata.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Hidden
    private Long cod_producto;

    @Column(name = "nombre_producto")
    @NotNull(message = "{NotNull.productos}")
    @Schema(example = "Silla Plegable",description = "Especifica el nombre del producto.")
    private String nombreProducto;

    @Column(name = "descripcion")
    @Schema(example = "Altura 20 x Anchura 30",description = "Detalles del producto.")
    private String descripcion;

    @Column(name = "precio")
    @NotNull(message = "{NotNull.productos}")
    @Schema(example = "11.60",description = "Especifica el precio del prodcto")
    private float precio;

    @Column(name = "cantidad_en_stock")
    @NotNull(message = "{NotNull.productos}")
    @Schema(example = "1560",description = "Entero que nos dice la cantidad de productos en stock")
    private int cantidadEnStock;

    @Column(name = "categoria")
    @NotNull(message = "{NotNull.productos}")
    @Schema(example = "Ropa",description = "Categoria del producto")
    private String categoria;


    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<PedidosProductos> pedidoProductos;

}
