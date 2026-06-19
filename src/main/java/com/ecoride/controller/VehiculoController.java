package com.ecoride.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecoride.model.Vehiculo;
import com.ecoride.service.AlquilerService;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final AlquilerService alquilerService;

    public VehiculoController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    
    @GetMapping("/prioridad-carga")
    public List<Vehiculo> obtenerPrioridadCarga() {
        return alquilerService.obtenerFlotaPorBateria();
    }

    
    @GetMapping("/tarifa-descendente")
    public List<Vehiculo> obtenerTarifaDescendente() {
        return alquilerService.obtenerFlotaPorTarifaMayor();
    }
}
