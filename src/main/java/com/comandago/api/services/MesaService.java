package com.comandago.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comandago.api.models.Mesa;
import com.comandago.api.repositories.MesaRepository;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> listarTodasMesas() {
        return mesaRepository.findAll();
    }

    public Mesa buscarMesaPorId(Long id) {
        Optional<Mesa> mesaOptional = mesaRepository.findById(id);
        if(mesaOptional.isPresent())
            return mesaOptional.get();
        return null;
    }

    public Mesa criarMesa() {
        return mesaRepository.save(new Mesa());
    }

    public boolean atualizarMesa(Long id, Mesa mesaAtualizada) {
        Mesa mesa = buscarMesaPorId(id);
        if(mesa != null){
            if(mesaAtualizada.getEstaAtiva() != mesa.getEstaAtiva())
                mesa.setEstaAtiva(mesaAtualizada.getEstaAtiva());

            if(mesaAtualizada.getEstado() != mesa.getEstado())
                mesa.setEstado(mesaAtualizada.getEstado());

            mesaRepository.save(mesa);
            return true;
        }
        return false;
    }

    public boolean excluirMesa(Long id) {
        Optional<Mesa> mesaExistente = mesaRepository.findById(id);
        if (mesaExistente.isPresent()) {
            mesaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
