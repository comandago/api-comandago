package com.comandago.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comandago.api.models.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long>{
    
}
