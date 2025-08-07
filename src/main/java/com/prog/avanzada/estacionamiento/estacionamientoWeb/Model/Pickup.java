package com.prog.avanzada.estacionamiento.estacionamientoWeb.Model;


public class Pickup extends Vehiculo {
    @Override
    public double obtenerRecargo() {
        return 0.20;
    }
}
