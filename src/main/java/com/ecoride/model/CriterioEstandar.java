package com.ecoride.model;

public class CriterioEstandar implements CriterioTarifa {
    @Override
    public double calcularCosto(int minutos, double tarifaBaseVehiculo) {
        return minutos * tarifaBaseVehiculo;
    }
}
