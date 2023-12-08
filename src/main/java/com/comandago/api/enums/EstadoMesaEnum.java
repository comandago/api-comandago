package com.comandago.api.enums;

public enum EstadoMesaEnum {
    OCUPADA("ocupada"),
    LIVRE("livre");

    private String estado;

    EstadoMesaEnum(String estado){
        this.estado = estado;
    }

    public String getEstado(){
        return this.estado;
    }
}
