package com.comandago.api.models;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.comandago.api.enums.EstadoMesaEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_mesas")
@Getter
@Setter
@AllArgsConstructor
public class Mesa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private EstadoMesaEnum estado;

    @NotNull
    private boolean estaAtiva;

    private Long idComanda;

    public boolean getEstaAtiva() {
        return estaAtiva;
    }

    public void setEstaAtiva(boolean estaAtiva) {
        this.estaAtiva = estaAtiva;
    }

    public Mesa(){
        this.estado = EstadoMesaEnum.LIVRE;
        this.estaAtiva = true;
    }
}
