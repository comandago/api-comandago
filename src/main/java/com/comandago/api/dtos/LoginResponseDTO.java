package com.comandago.api.dtos;

import com.comandago.api.enums.AtribuicaoUsuarioEnum;

public record LoginResponseDTO(Long id, AtribuicaoUsuarioEnum atribuicao, String token) {
    
}
