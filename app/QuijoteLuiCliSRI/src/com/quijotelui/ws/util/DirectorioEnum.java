package com.quijotelui.ws.util;

public enum DirectorioEnum {
    GENERADOS(1), FIRMADOS(2), AUTORIZADOS(3), NO_AUTORIZADOS(4), ENVIADOS(5);

    private int codigo;

    private DirectorioEnum(int codigo) {
        this.codigo = codigo;
    }

    public int getCode() {
        return this.codigo;
    }

    public void setCode(int codigo) {
        this.codigo = codigo;
    }

}
