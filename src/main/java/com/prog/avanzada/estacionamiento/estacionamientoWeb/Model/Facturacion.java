package com.prog.avanzada.estacionamiento.estacionamientoWeb.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "facturacion")
public class Facturacion {

    @Id
    @Column(name = "id_facturacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFacturacion;

    private LocalDateTime fechaFactura;
    private String patente;
    private String tipoVehiculo;
    private int codigoCochera;
    private String tipoContrato;
    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaEgreso;
    private String duracion;
    private String servicio;
    private int descuento;
    private BigDecimal montoTotal;

    public Facturacion(LocalDateTime fechaFactura, String patente, String tipoVehiculo, int codigoCochera, String tipoContrato, LocalDateTime fechaIngreso, LocalDateTime fechaEgreso, String duracion, String servicio, int descuento, BigDecimal montoTotal) {
        this.fechaFactura = fechaFactura;
        this.patente = patente;
        this.tipoVehiculo = tipoVehiculo;
        this.codigoCochera = codigoCochera;
        this.tipoContrato = tipoContrato;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.duracion = duracion;
        this.servicio = servicio;
        this.descuento = descuento;
        this.montoTotal = montoTotal;
    }

    public Facturacion() {

    }

    public LocalDateTime getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(LocalDateTime fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public int getCodigoCochera() {
        return codigoCochera;
    }

    public void setCodigoCochera(int codigoCochera) {
        this.codigoCochera = codigoCochera;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDateTime getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(LocalDateTime fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    @Override
    public String toString() {
        return "Facturacion{" +
                "idFacturacion=" + idFacturacion +
                ", fechaFactura=" + fechaFactura +
                ", patente='" + patente + '\'' +
                ", tipoVehiculo='" + tipoVehiculo + '\'' +
                ", codigoCochera=" + codigoCochera +
                ", tipoContrato='" + tipoContrato + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", fechaEgreso=" + fechaEgreso +
                ", duracion='" + duracion + '\'' +
                ", servicio='" + servicio + '\'' +
                ", descuento=" + descuento +
                ", montoTotal=" + montoTotal +
                '}';
    }
}
