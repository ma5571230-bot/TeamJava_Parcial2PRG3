package com.ecoride.model;

public class UsuarioRegular extends Usuario {

    public UsuarioRegular(int id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double aplicarDescuento(double monto) {
        return monto;
    }
}