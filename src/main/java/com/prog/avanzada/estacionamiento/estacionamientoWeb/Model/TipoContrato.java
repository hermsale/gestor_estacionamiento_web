package com.prog.avanzada.estacionamiento.estacionamientoWeb.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tipo_contrato")
public class TipoContrato {

    @Id
    @Column(name = "id_tipo_contrato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoContrato;

    @Column(name = "descripcion_contrato")
    private String nombreContrato;
    @Column(name = "precio_base")
    private BigDecimal precioBaseCochera;
    @Column(name = "descuento_servicio")
    private BigDecimal descuentoServicio;

    public Long getIdTipoContrato() {
        return idTipoContrato;
    }

    public String getNombreContrato() {
        return nombreContrato;
    }

    public void setNombreContrato(String nombreContrato) {
        this.nombreContrato = nombreContrato;
    }

    public void setPrecioBaseCochera(BigDecimal precioBaseCochera) {
        this.precioBaseCochera = precioBaseCochera;
    }

    public void setDescuentoServicio(BigDecimal descuentoServicio) {
        this.descuentoServicio = descuentoServicio;
    }

    public BigDecimal getPrecioBaseCochera() {
        return precioBaseCochera;
    }

    public BigDecimal getDescuentoServicio() {
        return descuentoServicio;
    }

    public void setIdTipoContrato(Long aLong) {

    }
}