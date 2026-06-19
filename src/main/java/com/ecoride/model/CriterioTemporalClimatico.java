package com.ecoride.model;

public class CriterioTemporalClimatico implements CriterioTarifa {
    @Override
    public double calcularCosto(int minutos, double tarifaBaseVehiculo) {
        double costoBase = minutos * tarifaBaseVehiculo;
        return costoBase + 150.0; // Suma un recargo de seguridad plano de $150 al total
    }
}
