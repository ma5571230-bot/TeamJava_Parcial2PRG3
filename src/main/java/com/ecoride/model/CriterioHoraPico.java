package com.ecoride.model;

public class CriterioHoraPico implements CriterioTarifa {
    @Override
    public double calcularCosto(int minutos, double tarifaBaseVehiculo) {
        double costoBase = minutos * tarifaBaseVehiculo;
        return costoBase * 1.40; // Recargo extra del 40% sobre el costo final calculado
    }
}
