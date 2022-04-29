package com.jorgemartinezruiz.enums;

public enum Buscar {
    NOMBRE("Nombre"),
    ID("Id");


    private String valor;

    Buscar(String valor) { this.valor=valor; }
    public String getValor() {
        return valor;
    }
    }

