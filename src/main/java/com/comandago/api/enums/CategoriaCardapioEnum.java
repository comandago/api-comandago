package com.comandago.api.enums;

public enum CategoriaCardapioEnum {
    BEBIDA("bebida"),
    PRATO("prato"),
    SOBREMESA("sobremesa");

    private String cardapio;

    CategoriaCardapioEnum(String cardapio){
        this.cardapio = cardapio;
    }

    public String getCardapio(){
        return this.cardapio;
    }
}
