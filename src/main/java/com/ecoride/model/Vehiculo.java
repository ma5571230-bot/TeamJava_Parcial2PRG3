package com.ecoride.model;

public abstract class Vehiculo implements Comparable<Vehiculo> {

    private String patente;
    protected int bateria; 
    private double tarifaBase;
    private EstadoVehiculo estado; 

    public Vehiculo(String patente, int bateria, double tarifaBase) {
        this.patente = patente;
        this.bateria = bateria;
        this.tarifaBase = tarifaBase;
        this.estado = new EstadoEnEspera(); 
    }


    @Override
    public int compareTo(Vehiculo otro) {
        if (this.bateria < otro.bateria) {
            return -1; 
        } else if (this.bateria > otro.bateria) {
            return 1;  
        }
        return 0;     
    }

    public void iniciarViaje() {
        this.estado.iniciarViaje(this);
    }

    public void finalizarViaje() {
        this.estado.finalizarViaje(this);
    }

    public void enviarAReparacion() {
        this.estado.enviarAReparacion(this);
    }

    public void resolverReparacion() {
        this.estado.resolverReparacion(this);
    }

    public EstadoVehiculo getEstado() {
        return estado;
    }

    public void setEstado(EstadoVehiculo estado) {
        this.estado = estado;
    }

    public String getPatente() {
        return patente;
    }

    public int getBateria() {
        return bateria;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }
}