package com.ecoride.config;

import java.util.ArrayList;
import java.util.List;

import com.ecoride.model.BicicletaElectrica;
import com.ecoride.model.Monopatin;
import com.ecoride.model.Usuario;
import com.ecoride.model.UsuarioPremium;
import com.ecoride.model.UsuarioRegular;
import com.ecoride.model.Vehiculo;

public class DataLoader {

    public static List<Usuario> usuarios = new ArrayList<>();
    public static List<Vehiculo> vehiculos = new ArrayList<>();

    static {
        usuarios.add(new UsuarioPremium(1, "Mayra", 0.10));
        usuarios.add(new UsuarioRegular(2, "Juan"));

        vehiculos.add(new Monopatin("BSB111", 80, 500, true));
        vehiculos.add(new BicicletaElectrica("EUN158", 10, 700, 50));
    }
}