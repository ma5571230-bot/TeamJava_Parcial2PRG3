package com.ecoride.service;

import com.ecoride.dto.RespuestaDTO;
import com.ecoride.exception.BateriaInsuficienteException;
import com.ecoride.exception.VehiculoNoEncontradoException;
import com.ecoride.model.*;
import com.ecoride.pago.Pago;
import com.ecoride.pago.PagoVirtual;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlquilerService {

    private List<Usuario> usuarios = new ArrayList<>();
    private List<Estacion> estaciones = new ArrayList<>();

    public AlquilerService() {

        usuarios.add(new UsuarioPremium(1, "Mayra", 0.10));
        usuarios.add(new UsuarioRegular(2, "Juan"));

        List<Vehiculo> vehiculos = new ArrayList<>();

        vehiculos.add(new Monopatin("BSB111", 80, 500, true));
        vehiculos.add(new BicicletaElectrica("EUN158", 10, 700, 50));

        estaciones.add(new Estacion("Centro", vehiculos));
    }

    public RespuestaDTO desbloquear(int idUsuario, String patente, String metodoPago) {

        Vehiculo vehiculoEncontrado = null;

        for (Estacion e : estaciones) {

            vehiculoEncontrado = e.buscarVehiculo(patente);

            if (vehiculoEncontrado != null) {
                break;
            }
        }

        if (vehiculoEncontrado == null) {
            throw new VehiculoNoEncontradoException("Vehiculo No Encontrado");
        }

        if (vehiculoEncontrado.getBateria() < 15) {
            throw new BateriaInsuficienteException("Bateria Insuficiente");
        }

        Usuario usuarioEncontrado = null;

        for (Usuario u : usuarios) {

            if (u.getId() == idUsuario) {
                usuarioEncontrado = u;
                break;
            }
        }

        double monto = usuarioEncontrado.aplicarDescuento(
                vehiculoEncontrado.getTarifaBase()
        );

        Pago pago = PagoVirtual.crearPago(metodoPago);

        pago.procesarPago(monto);

        return new RespuestaDTO(
                "Vehiculo desbloqueado correctamente",
                monto
        );
    }
}