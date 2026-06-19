package com.ecoride.model;

public class EstadoEnReparacion implements EstadoVehiculo {
    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        // RESTRICCIÓN EXPLICITA: "El sistema no debe permitir bajo ninguna circunstancia que se inicie viaje con él."
        throw new IllegalStateException("Operación rechazada: El vehículo se encuentra en reparación por fallas mecánicas o de batería.");
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        throw new IllegalStateException("No se puede finalizar un viaje en un vehículo que está siendo reparado.");
    }

    @Override
    public void enviarAReparacion(Vehiculo vehiculo) {
        throw new IllegalStateException("El vehículo ya está registrado en el taller.");
    }

    @Override
    public void resolverReparacion(Vehiculo vehiculo) {
        // Transición válida: Una vez reparado, vuelve a estar disponible para retiro
        vehiculo.setEstado(new EstadoEnEspera());
    }

    @Override
    public String getNombreEstado() {
        return "EN_REPARACION";
    }
}