package com.ecoride.model;

public interface EstadoVehiculo {
    void iniciarViaje(Vehiculo vehiculo);
    void finalizarViaje(Vehiculo vehiculo);
    void enviarAReparacion(Vehiculo vehiculo);
    void resolverReparacion(Vehiculo vehiculo);
    String getNombreEstado();
}