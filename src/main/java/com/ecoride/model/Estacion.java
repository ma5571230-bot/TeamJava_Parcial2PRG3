package com.ecoride.model;

import java.util.List;

public class Estacion {

    private String nombre;
    private List<Vehiculo> vehiculos;

    public Estacion(String nombre, List<Vehiculo> vehiculos) {
        this.nombre = nombre;
        this.vehiculos = vehiculos;
    }

    public Vehiculo buscarVehiculo(String patente) {

        for (Vehiculo v : vehiculos) {
            if (v.getPatente().equalsIgnoreCase(patente)) {
                return v;
            }
        }

        return null;
    }
}