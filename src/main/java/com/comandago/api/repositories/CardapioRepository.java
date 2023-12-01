package com.comandago.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comandago.api.models.Cardapio;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Long>{
    
}
