package com.example.springdata.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Hidden
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @Schema(example = "1",description = "Clave foranea,que es la principal del pedido")
    private Pedidos pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @Schema(example = "1",description = "Clave foranea,que es la principal del producto")
    private Productos producto;

    @Column(name = "cantidad")
    @Max(100)
    @Schema(example = "20",description = "Cantidad de productos que estan en el pedido")
    private int cantidadProductos;
}
