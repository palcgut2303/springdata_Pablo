package com.example.springdata.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

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


    @ManyToOne
    @JoinColumn(name = "id")
    private Usuario usuario;


    @OneToMany(mappedBy = "pedido")
    private ArrayList<PedidoProducto> productosEnPedidos;
}
