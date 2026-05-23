package com.ecoride.model;

public abstract class Usuario {

    protected int id;
    protected String nombre;

    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public abstract double aplicarDescuento(double monto);
}