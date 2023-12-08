package com.comandago.api.services;

import com.comandago.api.models.Cardapio;
import com.comandago.api.repositories.CardapioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardapioService {

    @Autowired
    private CardapioRepository cardapioRepository;

    public List<Cardapio> listarCardapios() {
        return cardapioRepository.findAll();
    }

    public Cardapio obterCardapioPorId(Long id) {
        Optional<Cardapio> cardapioOptional = cardapioRepository.findById(id);
        return cardapioOptional.orElse(null);
    }

    public Cardapio criarCardapio(Cardapio cardapio) {
        if(cardapio != null)
            return cardapioRepository.save(cardapio);
        return null;
    }

    public Cardapio atualizarCardapio(Long id, Cardapio cardapioAtualizado) {
        Optional<Cardapio> cardapioOptional = cardapioRepository.findById(id);
        if (cardapioOptional.isPresent()) {
            Cardapio cardapioExistente = cardapioOptional.get();
            cardapioExistente.setNome(cardapioAtualizado.getNome());
            cardapioExistente.setCategoria(cardapioAtualizado.getCategoria());
            cardapioExistente.setValor(cardapioAtualizado.getValor());
            cardapioExistente.setEstaAtivo(cardapioAtualizado.getEstaAtivo());
            
            return cardapioRepository.save(cardapioExistente);
        } 

        return null;
    }

    public boolean excluirCardapio(Long id) {
        Optional<Cardapio> cardapioOptional = cardapioRepository.findById(id);

        if (cardapioOptional.isPresent()) {
            cardapioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
