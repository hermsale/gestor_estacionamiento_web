package com.prog.avanzada.estacionamiento.estacionamientoWeb.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "servicio")
public class Servicio {

    @Id
    @Column(name = "id_servicio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicio;

    @Column(name = "descripcion_servicio")
    private String descripcionServicio;
    @Column(name = "precio_base")
    private BigDecimal costoServicio;

    public Long getIdServicio() {
        return idServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setCostoServicio(BigDecimal costoServicio) {
        this.costoServicio = costoServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public BigDecimal getCostoServicio() {
        return costoServicio;
    }

    @Override
    public String toString() {
        return descripcionServicio + " - $" + costoServicio;
    }

    public void setIdServicio(Long aLong) {
    }
}
