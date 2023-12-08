package com.comandago.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comandago.api.models.PedidosCardapio;

@Repository
public interface PedidosCardapioRepository extends JpaRepository<PedidosCardapio, Long>{
    
}
