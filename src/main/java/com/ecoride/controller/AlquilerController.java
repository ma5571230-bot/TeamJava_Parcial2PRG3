package com.ecoride.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecoride.config.DataLoader;
import com.ecoride.dto.RespuestaDTO;
import com.ecoride.dto.SolicitudAlquilerDTO;
import com.ecoride.model.AlertaGPS;
import com.ecoride.model.CriterioEstandar;
import com.ecoride.model.CriterioHoraPico;
import com.ecoride.model.CriterioTemporalClimatico;
import com.ecoride.model.Usuario;
import com.ecoride.service.AlquilerService;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {

    private final AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @PostMapping("/desbloquear")
    public RespuestaDTO desbloquear(@RequestBody SolicitudAlquilerDTO dto) {
        return alquilerService.desbloquear(dto.getIdUsuario(), dto.getPatente(), dto.getMetodoPago());
    }

    @PostMapping("/tarifa")
    public String cambiarCriterioTarifa(@RequestParam String tipoTarifa) {
        if (tipoTarifa.equalsIgnoreCase("PICO")) {
            alquilerService.setCriterioTarifa(new CriterioHoraPico());
            return "Criterio cambiado a: HORA PICO";
        } else if (tipoTarifa.equalsIgnoreCase("CLIMA")) {
            alquilerService.setCriterioTarifa(new CriterioTemporalClimatico());
            return "Criterio cambiado a: TEMPORAL CLIMÁTICO";
        } else {
            alquilerService.setCriterioTarifa(new CriterioEstandar());
            return "Criterio restablecido al ESTÁNDAR";
        }
    }

    @PostMapping("/alertas/procesar")
    public List<AlertaGPS> procesarAlertas(@RequestBody List<AlertaGPS> alertasMasivas) {
        return alquilerService.procesarAlertasDeduplicadas(alertasMasivas);
    }

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        return DataLoader.usuarios;
    }
}
   