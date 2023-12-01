package com.comandago.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comandago.api.dtos.ConsultarPedidosComandaDTO;
import com.comandago.api.dtos.EstadoPedidoDTO;
import com.comandago.api.dtos.ItemPedidoDTO;
import com.comandago.api.dtos.PedidoDTO;
import com.comandago.api.dtos.RespostaPedidoDTO;
import com.comandago.api.models.Pedido;
import com.comandago.api.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obterPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.obterPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/comanda/{id}")
    public ResponseEntity<List<ConsultarPedidosComandaDTO>> listarPedidosComanda(@PathVariable Long id){
        List<ConsultarPedidosComandaDTO> resposta = pedidoService.listarPedidosComanda(id);
        if(resposta != null)
            return ResponseEntity.ok(resposta);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/pedido/{id}")
    public ResponseEntity<Void> adicionarItensPedido(@PathVariable Long id, @RequestBody List<ItemPedidoDTO> itens){
        boolean resposta = pedidoService.adicionarItens(id, itens);
        if(resposta)
            return ResponseEntity.ok().build();
            
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/comanda/{id}")
    public ResponseEntity<RespostaPedidoDTO> criarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedido) {
        if(pedido != null){
            Long idPedido = pedidoService.criarPedido(id, pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RespostaPedidoDTO(idPedido));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @Valid @RequestBody Pedido pedidoAtualizado) {
        Pedido pedido = pedidoService.atualizarPedido(id, pedidoAtualizado);
        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        pedidoService.excluirPedido(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/itens/add/{id}")
    public ResponseEntity<Void> adicionarItemPedido(@PathVariable Long id, @RequestBody ItemPedidoDTO item){
        var pedido = pedidoService.obterPedidoPorId(id);
        if(pedido != null){
            pedidoService.adicionarItem(pedido, item);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/estado/{id}")
    public ResponseEntity<RespostaPedidoDTO> alterarEstadoPedido(@PathVariable Long id, @RequestBody EstadoPedidoDTO estado){
        Long resposta = pedidoService.alterarEstadoPedido(id, estado);
        if(resposta > 0)
            return ResponseEntity.ok(new RespostaPedidoDTO(resposta));
        if(resposta == 0)
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/estado")
    public ResponseEntity<List<Pedido>> buscarPedidosPorEstado(@RequestBody EstadoPedidoDTO estadoPedido){
        List<Pedido> pedidos = pedidoService.buscarPedidosPorEstado(estadoPedido);
        return ResponseEntity.ok(pedidos);
    }
}
