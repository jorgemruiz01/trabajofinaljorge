package com.jorgemartinezruiz.enums;

public enum UsuariosLogin {


    ADMINISTRADOR("Administrador"),
    EMPLEADO("Empleado"),
    CLIENTE("Cliente");

    private String valor;

    UsuariosLogin(String valor) { this.valor=valor; }
    public String getValor() {
        return valor;
    }
}
