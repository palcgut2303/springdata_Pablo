package com.example.springdata.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PedidosProductos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidosProductos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedidos pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Productos producto;

    @Column(name = "cantidad")
    @Max(100)
    private int cantidadProductos;
}
