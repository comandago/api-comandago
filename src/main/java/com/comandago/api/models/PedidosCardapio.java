package com.comandago.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pedidos_cardapio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidosCardapio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pedidoId")
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Pedido pedido;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cardapioId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cardapio cardapio;

    @NotNull
    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = true)
    private String observacoes;

}
