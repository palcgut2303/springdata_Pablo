package com.example.springdata.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1",description = "Codigo Identificador del pedido, autoincremental")
    private Long cod_pedido;

    @Column(name = "direccion_envio",nullable = false)
    @NotBlank(message = "{NotBlank.pedidos.direccionEnvio}")
    @Size(min = 5)
    @Schema(example = "123 CALLE MALAGA, CÓRDOBA",description = "direccion de envio del pedido")
    private String direccionEnvio;

    @Column(name = "estadoPedido",nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    @Schema(example = "EN_PREPARACION",description = "Tipo enum, se define el estado del pedido para un seguimiento.")
    private estadoPedido estadoPedido;

    @Column(name = "fecha_entrega")
    @NotNull
    @Schema(example = "2024-01-18",description = "Se especifica que dia sera entregado el pedido")
    private LocalDate fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "id")
    private Usuario usuario;


    @OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<PedidosProductos> pedidoProductos;
}
