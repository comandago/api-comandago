package com.comandago.api.dtos;

import com.comandago.api.enums.AtribuicaoUsuarioEnum;

public record RegisterDTO(String nome, String login, String senha, AtribuicaoUsuarioEnum atribuicao) {
    
}
