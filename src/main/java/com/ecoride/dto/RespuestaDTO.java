package com.ecoride.dto;

public class RespuestaDTO {

    private String mensaje;
    private double monto;

    public RespuestaDTO(String mensaje, double monto) {
        this.mensaje = mensaje;
        this.monto = monto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public double getMonto() {
        return monto;
    }
}
