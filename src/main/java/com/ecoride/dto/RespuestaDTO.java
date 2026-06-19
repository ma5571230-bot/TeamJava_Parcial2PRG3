package com.ecoride.dto;

public class RespuestaDTO {
    private String patente;
    private double costoFinal;
    private int tiempoTranscurrido; 
    private String faseActual; 

    public RespuestaDTO(String patente, double costoFinal, int tiempoTranscurrido, String faseActual) {
        this.patente = patente;
        this.costoFinal = costoFinal;
        this.tiempoTranscurrido = tiempoTranscurrido;
        this.faseActual = faseActual;
    }

    public String getPatente() { return patente; }
    public double getCostoFinal() { return costoFinal; }
    public int getTiempoTranscurrido() { return tiempoTranscurrido; }
    public String getFaseActual() { return faseActual; }
}
