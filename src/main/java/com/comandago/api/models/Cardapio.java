package com.comandago.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.comandago.api.enums.CategoriaCardapioEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_cardapio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cardapio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    @Enumerated
    @Column(nullable = false)
    private CategoriaCardapioEnum categoria;

    @NotNull
    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false)
    private Double valor;

    private boolean estaAtivo = true;

    public boolean getEstaAtivo(){
        return this.estaAtivo;
    }
}
