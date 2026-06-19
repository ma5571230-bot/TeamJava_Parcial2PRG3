package com.ecoride.model;

public class EstadoEnViaje implements EstadoVehiculo {
    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehículo ya se encuentra en un viaje activo y no puede ser alquilado.");
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        // Transición válida: Al terminar, vuelve a la estación
        vehiculo.setEstado(new EstadoEnEspera());
    }

    @Override
    public void enviarAReparacion(Vehiculo vehiculo) {
        throw new IllegalStateException("No se puede enviar al taller un vehículo que está en pleno viaje.");
    }

    @Override
    public void resolverReparacion(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehículo está en ruta, no en reparación.");
    }

    @Override
    public String getNombreEstado() {
        return "EN_VIAJE";
    }
}