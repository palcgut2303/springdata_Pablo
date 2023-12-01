package com.example.springdata.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pedido_producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad")
    private int cantidadProductos;

    @Column(name = "estadoPedido",nullable = false)
    private estadoPedido estadoPedido;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "cod_pedido")
    private Pedidos pedido;
    @ManyToOne
    @JoinColumn(name = "cod_producto")
    private Productos producto;
}
