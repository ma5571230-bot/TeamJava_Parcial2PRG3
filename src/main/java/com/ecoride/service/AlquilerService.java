package com.ecoride.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ecoride.config.DataLoader; 
import com.ecoride.dto.RespuestaDTO;
import com.ecoride.exception.BateriaInsuficienteException;
import com.ecoride.exception.VehiculoNoEncontradoException;
import com.ecoride.model.AlertaGPS;
import com.ecoride.model.ComparadorPrecioMayorAMenor;
import com.ecoride.model.CriterioEstandar;
import com.ecoride.model.CriterioTarifa;
import com.ecoride.model.Usuario;
import com.ecoride.model.Vehiculo;
import com.ecoride.pago.Pago;
import com.ecoride.pago.PagoVirtual;

@Service
public class AlquilerService {

    private Map<String, Vehiculo> mapaVehiculosRapido = new HashMap<>();
    private CriterioTarifa criterioTarifaActual;

    public AlquilerService() {
        this.criterioTarifaActual = new CriterioEstandar();
        
        for (Vehiculo v : DataLoader.vehiculos) {
            mapaVehiculosRapido.put(v.getPatente(), v);
        }
    }

    public void setCriterioTarifa(CriterioTarifa nuevoCriterio) {
        if (nuevoCriterio != null) {
            this.criterioTarifaActual = nuevoCriterio;
        }
    }

    public RespuestaDTO desbloquear(int idUsuario, String patente, String metodoPago) {

        Vehiculo vehiculoEncontrado = mapaVehiculosRapido.get(patente);

        if (vehiculoEncontrado == null) {
            throw new VehiculoNoEncontradoException("Vehiculo No Encontrado");
        }

        vehiculoEncontrado.iniciarViaje();

        if (vehiculoEncontrado.getBateria() < 15) {
            vehiculoEncontrado.finalizarViaje(); 
            throw new BateriaInsuficienteException("Bateria Insuficiente");
        }

        Usuario usuarioEncontrado = null;
        for (Usuario u : DataLoader.usuarios) {
            if (u.getId() == idUsuario) {
                usuarioEncontrado = u;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + idUsuario);
        }

        double costoBaseEstrategia = criterioTarifaActual.calcularCosto(1, vehiculoEncontrado.getTarifaBase());
        double monto = usuarioEncontrado.aplicarDescuento(costoBaseEstrategia);

        Pago pago = PagoVirtual.crearPago(metodoPago);
        pago.procesarPago(monto);

        String nombreEstadoAmigable = vehiculoEncontrado.getEstado().getClass().getSimpleName().replace("EstadoEn", "");

        return new RespuestaDTO(
                vehiculoEncontrado.getPatente(),
                monto,
                1, 
                nombreEstadoAmigable
        );
    }

    public double finalizarViajeVoluntario(String patente, int minutos) {
        Vehiculo vehiculo = mapaVehiculosRapido.get(patente);
        if (vehiculo == null) {
            throw new VehiculoNoEncontradoException("Vehiculo No Encontrado");
        }

        vehiculo.finalizarViaje();
        return criterioTarifaActual.calcularCosto(minutos, vehiculo.getTarifaBase());
    }

    public List<AlertaGPS> procesarAlertasDeduplicadas(List<AlertaGPS> alertasMasivas) {
        if (alertasMasivas == null) {
            return new ArrayList<>();
        }

        List<AlertaGPS> alertasLimpias = new ArrayList<>();
        Set<String> idsProcesados = new HashSet<>();

        for (int i = 0; i < alertasMasivas.size(); i++) {
            AlertaGPS alerta = alertasMasivas.get(i);
            if (!idsProcesados.contains(alerta.getIdAlerta())) {
                idsProcesados.add(alerta.getIdAlerta());
                alertasLimpias.add(alerta);              
            }
        }
        return alertasLimpias;
    }

    private List<Vehiculo> obtenerListaFlota() {
        List<Vehiculo> lista = new ArrayList<>();
        for (Vehiculo v : mapaVehiculosRapido.values()) {
            lista.add(v);
        }
        return lista;
    }

    public List<Vehiculo> obtenerFlotaPorBateria() {
        List<Vehiculo> lista = obtenerListaFlota();
        Collections.sort(lista); 
        return lista;
    }

    public List<Vehiculo> obtenerFlotaPorTarifaMayor() {
        List<Vehiculo> lista = obtenerListaFlota();
        Collections.sort(lista, new ComparadorPrecioMayorAMenor());
        return lista;
    }
}