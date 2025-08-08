package com.prog.avanzada.estacionamiento.estacionamientoWeb.Repository;

import com.prog.avanzada.estacionamiento.estacionamientoWeb.Model.Cochera;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Model.EstadoCochera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CocheraRepository extends JpaRepository<Cochera, Long> {

    Optional<Cochera> findByPatente(String patente);

    Optional<Cochera> findByCodigoCochera(int codigoCochera);

}
