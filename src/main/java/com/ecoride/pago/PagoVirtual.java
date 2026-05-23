package com.ecoride.pago;

public class PagoVirtual {


    public static Pago crearPago(String tipo) {

        if (tipo.equalsIgnoreCase("TARJETA")) {
            return new PagoTarjeta();
        }

        return new PagoBilletera();
    }
} 
