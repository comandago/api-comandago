package com.comandago.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.comandago.api.models.PedidosCardapio;
import com.comandago.api.repositories.PedidosCardapioRepository;

@Service
public class PedidosCardapioService {

    final PedidosCardapioRepository pedidosCardapioRepository;

    PedidosCardapioService(PedidosCardapioRepository pedidosCardapioRepository) {
        this.pedidosCardapioRepository = pedidosCardapioRepository;
    }

    public Optional<PedidosCardapio> buscarPorId(Long id) {
        return pedidosCardapioRepository.findById(id);
    }

    public List<PedidosCardapio> listarPedidosCardapio() {
        return pedidosCardapioRepository.findAll();
    }

    public PedidosCardapio criarPedidosCardapio(PedidosCardapio pedidosCardapio) {
        if(pedidosCardapio != null)
            return pedidosCardapioRepository.save(pedidosCardapio);
        return null;
    }

    public PedidosCardapio atualizarPedidosCardapio(Long id, PedidosCardapio pedidosCardapio) {
        if (pedidosCardapioRepository.existsById(id)) {
            pedidosCardapio.setId(id);
            return pedidosCardapioRepository.save(pedidosCardapio);
        } else {
            throw new IllegalArgumentException("Pedido de cardápio não encontrado.");
        }
    }

    public void excluirPedidosCardapio(Long id) {
        if (pedidosCardapioRepository.existsById(id)) {
            pedidosCardapioRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Pedido de cardápio não encontrado.");
        }
    }
}
