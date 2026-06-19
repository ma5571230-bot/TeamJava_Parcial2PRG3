package com.ecoride.model;

public class EstadoEnEspera implements EstadoVehiculo {
    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        // Transición válida: Pasa a estar conducido por el usuario
        vehiculo.setEstado(new EstadoEnViaje());
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehículo está libre en la estación. No hay viaje que finalizar.");
    }

    @Override
    public void enviarAReparacion(Vehiculo vehiculo) {
        // Transición válida: Si tiene fallas, se retira al taller
        vehiculo.setEstado(new EstadoEnReparacion());
    }

    @Override
    public void resolverReparacion(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehículo no se encuentra en el taller de reparación.");
    }

    @Override
    public String getNombreEstado() {
        return "EN_ESPERA";
    }
}
