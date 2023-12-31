package com.comandago.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comandago.api.enums.EstadoPedidoEnum;
import com.comandago.api.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    public List<Pedido> findByEstado(EstadoPedidoEnum estado);
    
}
