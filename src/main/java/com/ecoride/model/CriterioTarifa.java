package com.ecoride.model;

public interface CriterioTarifa {
    double calcularCosto(int minutos, double tarifaBaseVehiculo);
}