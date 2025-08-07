package com.prog.avanzada.estacionamiento.estacionamientoWeb.Repository;

import com.prog.avanzada.estacionamiento.estacionamientoWeb.Model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    Optional<Object> findByDescripcionServicio(String descripcionServicio);
}
