package com.comandago.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.comandago.api.models.PedidosCardapio;
import com.comandago.api.services.PedidosCardapioService;

@RestController
@RequestMapping("/pedidos-cardapio")
public class PedidosCardapioController {

    @Autowired
    private PedidosCardapioService pedidosCardapioService;

    @GetMapping("/{id}")
    public ResponseEntity<PedidosCardapio> buscarPorId(@PathVariable Long id) {
        Optional<PedidosCardapio> pedidosCardapio = pedidosCardapioService.buscarPorId(id);
        return pedidosCardapio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PedidosCardapio>> listarPedidosCardapio() {
        List<PedidosCardapio> pedidosCardapioList = pedidosCardapioService.listarPedidosCardapio();
        return ResponseEntity.ok(pedidosCardapioList);
    }

    @PostMapping
    public ResponseEntity<PedidosCardapio> criarPedidosCardapio(@RequestBody PedidosCardapio pedidosCardapio) {
        if(pedidosCardapio != null){
            PedidosCardapio novoPedidosCardapio = pedidosCardapioService.criarPedidosCardapio(pedidosCardapio);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPedidosCardapio);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidosCardapio> atualizarPedidosCardapio(@PathVariable Long id, @RequestBody PedidosCardapio pedidosCardapio) {
        PedidosCardapio pedidosCardapioAtualizado = pedidosCardapioService.atualizarPedidosCardapio(id, pedidosCardapio);
        return ResponseEntity.ok(pedidosCardapioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedidosCardapio(@PathVariable Long id) {
        pedidosCardapioService.excluirPedidosCardapio(id);
        return ResponseEntity.noContent().build();
    }
}
