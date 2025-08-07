package com.prog.avanzada.estacionamiento.estacionamientoWeb.Model;

public abstract class Vehiculo {


    private String patente;
    private String descripcion;

    public Vehiculo() {

    }

    public abstract double obtenerRecargo();

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}