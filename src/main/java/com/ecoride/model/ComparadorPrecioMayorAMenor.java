package com.ecoride.model;

import java.util.Comparator;

public class ComparadorPrecioMayorAMenor implements Comparator<Vehiculo> {
    
    @Override
    public int compare(Vehiculo v1, Vehiculo v2) {
        if (v2.getTarifaBase() < v1.getTarifaBase()) {
            return -1;
        } else if (v2.getTarifaBase() > v1.getTarifaBase()) {
            return 1;
        }
        return 0;
    }
}