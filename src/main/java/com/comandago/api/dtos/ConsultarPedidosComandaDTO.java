package com.comandago.api.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.comandago.api.enums.EstadoPedidoEnum;
import com.comandago.api.models.Pedido;
import com.comandago.api.models.PedidosCardapio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultarPedidosComandaDTO {
    private Long idPedido;
    private String usuario;
    private Long mesa;
    private EstadoPedidoEnum estado;
    private List<PedidosCardapio> itens;
    private LocalDateTime dataHoraPedido;

    public ConsultarPedidosComandaDTO(Pedido pedido){
        this.setPedido(pedido);
    }
    
    public void setPedido(Pedido pedido){
        this.idPedido = pedido.getId();
        this.usuario = pedido.getUsuario();
        this.mesa = pedido.getMesa().getId();
        this.estado = pedido.getEstado();
        this.itens = pedido.getItens();
        this.dataHoraPedido = pedido.getDataHora();
    }
}
