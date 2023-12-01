package com.comandago.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comandago.api.models.Comanda;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long>{
    
    public List<Comanda> findByCheckout(boolean checkout);
}
