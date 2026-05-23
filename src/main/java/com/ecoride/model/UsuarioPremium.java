package com.ecoride.model;

public class UsuarioPremium extends Usuario {

    private double descuento;

    public UsuarioPremium(int id, String nombre, double descuento) {
        super(id, nombre);
        this.descuento = descuento;
    }

    @Override
    public double aplicarDescuento(double monto) {
        return monto - (monto * descuento);
    }
}
