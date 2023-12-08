package com.comandago.api.enums;

public enum AtribuicaoUsuarioEnum {
    CAIXA("caixa"),
    COZINHA("cozinha"),
    GARCOM("garcom"),
    SUPER_USUARIO("super_usuario");

    private String atribuicao;

    AtribuicaoUsuarioEnum(String atribuicao){
        this.atribuicao = atribuicao;
    }

    public String getAtribuicao(){
        return this.atribuicao;
    }
}
