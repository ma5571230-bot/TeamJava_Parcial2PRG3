package com.ecoride.pago;


public class PagoBilletera implements Pago {

    @Override
    public void procesarPago(double monto) {
        System.out.println("Cobro exitoso de $" + monto + " realizado con Billetera");
    }
}
