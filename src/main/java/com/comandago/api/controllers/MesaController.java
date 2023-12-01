package com.comandago.api.controllers;

import java.util.List;
import java.util.Optional;

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

import com.comandago.api.models.Mesa;
import com.comandago.api.repositories.MesaRepository;
import com.comandago.api.services.MesaService;

@RestController
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @Autowired
    private MesaRepository mesaRepository;

    @GetMapping
    public ResponseEntity<List<Mesa>> listarTodasMesas() {
        //List<Mesa> mesas = mesaRepository.findAll();
        //return new ResponseEntity<>(mesaService.listarTodasMesas(), HttpStatus.OK);
        return ResponseEntity.ok(mesaService.listarTodasMesas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> buscarMesaPorId(@PathVariable Long id) {
        // Optional<Mesa> mesa = mesaRepository.findById(id);
        // if (mesa.isPresent()) {
        //     return new ResponseEntity<>(mesa.get(), HttpStatus.OK);
        // } else {
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
        Optional<Mesa> mesa = mesaRepository.findById(id);
        if(mesa.isPresent())
            return ResponseEntity.ok(mesa.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Mesa> criarMesa() {
        // if(mesa != null){
        //     Mesa novaMesa = mesaRepository.save(mesa);
        //     return new ResponseEntity<>(novaMesa, HttpStatus.CREATED);
        // }
        // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(mesaService.criarMesa());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarMesa(@PathVariable Long id, @RequestBody Mesa mesaAtualizada) {
        // Optional<Mesa> mesaExistente = mesaRepository.findById(id);
        // if (mesaExistente.isPresent()) {
        //     Mesa mesa = mesaExistente.get();
        //     mesa.setEstado(mesaAtualizada.isEstado());
        //     mesa.setEstaAtiva(mesaAtualizada.isEstaAtiva());
        //     mesaRepository.save(mesa);
        //     return new ResponseEntity<>(mesa, HttpStatus.OK);
        // } else {
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
        boolean mesa = mesaService.atualizarMesa(id, mesaAtualizada);
        if(mesa)
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMesa(@PathVariable Long id) {
        // Optional<Mesa> mesaExistente = mesaRepository.findById(id);
        // if (mesaExistente.isPresent()) {
        //     mesaRepository.deleteById(id);
        //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        // } else {
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
        boolean mesa = mesaService.excluirMesa(id);
        if(mesa)
            return ResponseEntity.ok().build();
        
        return ResponseEntity.notFound().build();
    }
}
