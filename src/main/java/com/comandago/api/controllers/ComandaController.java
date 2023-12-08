package com.comandago.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comandago.api.dtos.CheckoutDTO;
import com.comandago.api.dtos.ComandaDTO;
import com.comandago.api.dtos.IdComandaDTO;
import com.comandago.api.models.Comanda;
import com.comandago.api.services.ComandaService;

@RestController
@RequestMapping("/comandas")
public class ComandaController {

    @Autowired
    private ComandaService comandaService;


    @GetMapping
    public ResponseEntity<List<Comanda>> listarComandas() {
        List<Comanda> comandas = comandaService.listarComandas();
        return ResponseEntity.ok(comandas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comanda> obterComandaPorId(@PathVariable Long id) {
        Comanda comanda = comandaService.obterComandaPorId(id);
        if (comanda != null) {
            return ResponseEntity.ok(comanda);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<IdComandaDTO> criarComanda(@RequestBody ComandaDTO comanda) {
        Long idComanda = comandaService.criarComanda(comanda);
        if(idComanda > 0){
            return ResponseEntity.ok(new IdComandaDTO(idComanda));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comanda> atualizarComanda(@PathVariable Long id, @RequestBody Comanda comanda) {
        Comanda comandaAtualizada = comandaService.atualizarComanda(id, comanda);
        if (comandaAtualizada != null) {
            return ResponseEntity.ok(comandaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComanda(@PathVariable Long id) {
        boolean sucesso = comandaService.deletarComanda(id);
        if (sucesso) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("checkout/{id}")
    public ResponseEntity<IdComandaDTO> checkoutComanda(@PathVariable Long id){
        Long retorno = comandaService.checkoutComanda(id);

        if(retorno != null)
            return ResponseEntity.ok(new IdComandaDTO(retorno));

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<List<Comanda>> buscarPorCheckout(@RequestBody CheckoutDTO checkout){
        List<Comanda> comandas = comandaService.buscarPorCheckout(checkout.checkout());
        if(comandas != null)
            return ResponseEntity.ok(comandas);

        return ResponseEntity.notFound().build();
    }
}