package com.prog.avanzada.estacionamiento.estacionamientoWeb.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cochera")
public class Cochera {

    @Id
    @Column(name = "id_cochera")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCochera;

    @Column(name = "codigo_cochera")
    private int codigoCochera;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoCochera estadoCochera;

    @Column(name = "fecha_entrada")
    private LocalDateTime fechaIngreso;
    @Column(name = "fecha_salida")
    private LocalDateTime fechaSalida;

    @Transient
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_tipo_contrato")
    private TipoContrato contrato;

    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;

    @Column(name = "patente")
    private String patente;

    @Column(name = "descripcion")
    private String descripcion;

    public Cochera( int codigoCochera, EstadoCochera estadoCochera, TipoContrato contrato, Servicio servicio, LocalDateTime fechaIngreso, LocalDateTime fechaSalida, String patente, String descripcion) {
        this.codigoCochera = codigoCochera;
        this.estadoCochera = estadoCochera;
        this.patente = patente;
        this.descripcion = descripcion;
        this.contrato = contrato;
        this.servicio = servicio;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    public Cochera() {

    }

    public String getPatente() {
        return patente;
    }

    public void setIdCochera(int idCochera) {
        this.idCochera = idCochera;
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

    public int getIdCochera() {
        return idCochera;
    }

    public int getCodigoCochera() {
        return codigoCochera;
    }

    public void setCodigoCochera(int codigoCochera) {
        this.codigoCochera = codigoCochera;
    }

    public EstadoCochera getEstadoCochera() {
        return estadoCochera;
    }

    public void setEstadoCochera(EstadoCochera estadoCochera) {
        this.estadoCochera = estadoCochera;
    }


    public TipoContrato getContrato() {
        return contrato;
    }

    public void setContrato(TipoContrato contrato) {
        this.contrato = contrato;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setVehiculo(Vehiculo vehiculo) {
    }
}
