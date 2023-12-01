package com.comandago.api.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_comandas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comanda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "mesaId")
    private Mesa mesa;

    @NotBlank
    @Column(nullable = false)
    private String nomeCliente;

    @Column(name = "total_a_pagar")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double totalAPagar = 0.0;

    @ManyToMany
    private List<Pedido> pedidos;

    private boolean checkout = false;

    public void addPedido(Pedido pedido){
        this.pedidos.add(pedido);
    }
}
