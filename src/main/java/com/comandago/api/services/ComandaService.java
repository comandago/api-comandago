package com.comandago.api.services;

import com.comandago.api.dtos.ComandaDTO;
import com.comandago.api.enums.EstadoMesaEnum;
import com.comandago.api.models.Comanda;
import com.comandago.api.models.Mesa;
import com.comandago.api.repositories.ComandaRepository;
import com.comandago.api.repositories.MesaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComandaService {

    @Autowired
    private ComandaRepository comandaRepository;

    @Autowired 
    private MesaRepository mesaRepository;


    public List<Comanda> listarComandas() {
        return comandaRepository.findAll();
    }

    public Comanda obterComandaPorId(Long id) {
        Optional<Comanda> comanda = comandaRepository.findById(id);
        return comanda.orElse(null);
    }

    public Long criarComanda(ComandaDTO comandaDTO) {
        Optional<Mesa> mesaOptional = mesaRepository.findById(comandaDTO.getIdMesa());
        Mesa mesa = mesaOptional.get();
        System.out.println(mesa.toString());
        if(mesaOptional.isPresent() && mesa.getEstado().equals(EstadoMesaEnum.LIVRE)){
            var comanda = new Comanda();
            comanda.setNomeCliente(comandaDTO.getNomeCliente());
            comanda.setMesa(mesa);
            comandaRepository.save(comanda);
            mesa.setEstado(EstadoMesaEnum.OCUPADA);
            mesa.setIdComanda(comanda.getId());
            mesaRepository.save(mesa);
            return comanda.getId();
        }
        return null;
    }

    public Comanda atualizarComanda(Long id, Comanda comandaAtualizada) {
        Optional<Comanda> comandaExistente = comandaRepository.findById(id);
        if (comandaExistente.isPresent()) {
            comandaAtualizada.setId(comandaExistente.get().getId());
            return comandaRepository.save(comandaAtualizada);
        } else {
            return null;
        }
    }

    public boolean deletarComanda(Long id) {
        Optional<Comanda> comanda = comandaRepository.findById(id);
        if (comanda.isPresent()) {
            comandaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Long checkoutComanda(Long idComanda){
        Optional<Comanda> comandaOptional = comandaRepository.findById(idComanda);
        if(comandaOptional.isPresent()){
            Comanda comanda = comandaOptional.get();
            comanda.setCheckout(true);
            comandaRepository.save(comanda);
            Optional<Mesa> mesaOptional = mesaRepository.findById(comanda.getMesa().getId());
            Mesa mesa = mesaOptional.get();
            mesa.setIdComanda(null);
            mesa.setEstado(EstadoMesaEnum.LIVRE);
            mesaRepository.save(mesa);

            return comanda.getId();
        }

        return null;
    }

    public List<Comanda> buscarPorCheckout(boolean checkout){
        List<Comanda> comandas = comandaRepository.findByCheckout(checkout);

        return comandas;
    }
}
