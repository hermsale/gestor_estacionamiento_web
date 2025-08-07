package com.prog.avanzada.estacionamiento.estacionamientoWeb.Repository;

import com.prog.avanzada.estacionamiento.estacionamientoWeb.Model.TipoContrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoContratoRepository extends JpaRepository<TipoContrato, Long> {

    Optional<Object> findByNombreContrato(String nombreContrato);
}
