package com.ecoride.model;

import java.util.Objects;

public class AlertaGPS {
    private String idAlerta;
    private String patente;
    private String codigoError; 
    private long timestamp;

    public AlertaGPS(String idAlerta, String patente, String codigoError, long timestamp) {
        this.idAlerta = idAlerta;
        this.patente = patente;
        this.codigoError = codigoError;
        this.timestamp = timestamp;
    }

    public String getIdAlerta() { return idAlerta; }
    public String getPatente() { return patente; }
    public String getCodigoError() { return codigoError; }
    public long getTimestamp() { return timestamp; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlertaGPS otra = (AlertaGPS) o;
        return Objects.equals(idAlerta, otra.idAlerta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAlerta);
    }
}