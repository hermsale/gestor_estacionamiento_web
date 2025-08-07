package com.prog.avanzada.estacionamiento.estacionamientoWeb.Repository;


import com.prog.avanzada.estacionamiento.estacionamientoWeb.Model.Facturacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturacionRepository extends JpaRepository<Facturacion, Long> {
}
