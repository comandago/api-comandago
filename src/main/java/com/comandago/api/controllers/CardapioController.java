package com.comandago.api.controllers;

import com.comandago.api.models.Cardapio;
import com.comandago.api.services.CardapioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {

    @Autowired
    private CardapioService cardapioService;

    @GetMapping
    public ResponseEntity<List<Cardapio>> listarCardapios() {
        return ResponseEntity.ok(cardapioService.listarCardapios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cardapio> obterCardapioPorId(@PathVariable Long id) {
        Cardapio cardapio = cardapioService.obterCardapioPorId(id);
        if (cardapio != null)
            return ResponseEntity.ok(cardapio);
        
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cardapio> criarCardapio(@Valid @RequestBody Cardapio cardapio) {
        if(cardapio != null){
            Cardapio novoCardapio = cardapioService.criarCardapio(cardapio);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoCardapio);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarCardapio(@PathVariable Long id, @RequestBody Cardapio cardapio) {
        Cardapio cardapioAtualizado = cardapioService.atualizarCardapio(id, cardapio);
        if (cardapioAtualizado != null)
            return ResponseEntity.ok().build();
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCardapio(@PathVariable Long id) {
        boolean sucesso = cardapioService.excluirCardapio(id);
        if (sucesso) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
