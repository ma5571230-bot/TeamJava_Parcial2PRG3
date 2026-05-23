package com.ecoride.controller;


import com.ecoride.dto.RespuestaDTO;
import com.ecoride.dto.SolicitudAlquilerDTO;
import com.ecoride.service.AlquilerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {

    private final AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @PostMapping("/desbloquear")
    public RespuestaDTO desbloquear(
            @RequestBody SolicitudAlquilerDTO dto
    ) {

        return alquilerService.desbloquear(
                dto.getIdUsuario(),
                dto.getPatente(),
                dto.getMetodoPago()
        );
    }
}
