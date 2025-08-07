package com.prog.avanzada.estacionamiento.estacionamientoWeb.Service;

import com.prog.avanzada.estacionamiento.estacionamientoWeb.Model.*;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Repository.CocheraRepository;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Repository.FacturacionRepository;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Repository.ServicioRepository;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Repository.TipoContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CocheraService {

    @Autowired
    private CocheraRepository cocheraRepository;

    @Autowired
    private FacturacionRepository facturacionRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private TipoContratoRepository tipoContratoRepository;

    public List<Cochera> getAll() {
        return cocheraRepository.findAll();
    }

    public Optional<Cochera> findByCodigo(int codigo) {
        return cocheraRepository.findByCodigoCochera(codigo);
    }

    public Optional<Cochera> findByPatente(String patente) {
        return cocheraRepository.findByPatente(patente);
    }
    public Cochera ingresarVehiculo(Cochera cochera) {

        Servicio servicioPersistido;
        if (cochera.getServicio().getIdServicio() != null) {
            servicioPersistido = servicioRepository.findById(cochera.getServicio().getIdServicio())
                    .orElseThrow(() -> new RuntimeException("Servicio no encontrado por ID"));
        } else {
            servicioPersistido = (Servicio) servicioRepository.findByDescripcionServicio(cochera.getServicio().getDescripcionServicio())
                    .orElseThrow(() -> new RuntimeException("Servicio no encontrado por descripción"));
        }

        TipoContrato contratoPersistido;
        if (cochera.getContrato().getIdTipoContrato() != null) {
            contratoPersistido = tipoContratoRepository.findById(cochera.getContrato().getIdTipoContrato())
                    .orElseThrow(() -> new RuntimeException("Contrato no encontrado por ID"));
        } else {
            contratoPersistido = (TipoContrato) tipoContratoRepository.findByNombreContrato(cochera.getContrato().getNombreContrato())
                    .orElseThrow(() -> new RuntimeException("Contrato no encontrado por nombre"));
        }

        cochera.setServicio(servicioPersistido);
        cochera.setContrato(contratoPersistido);
        cochera.setEstadoCochera(EstadoCochera.OCUPADO);
        cochera.setDescripcion(cochera.getDescripcion());
        cochera.setFechaIngreso(LocalDateTime.now());

        cocheraRepository.save(cochera);
        return cochera;
    }

    public Cochera liberarCochera(int codigoCochera) {
        Optional<Cochera> optional = cocheraRepository.findByCodigoCochera(codigoCochera);

        if (optional.isPresent()) {
            Cochera cochera = optional.get();
            cochera.setEstadoCochera(EstadoCochera.DISPONIBLE);
            cochera.setContrato(null);
            cochera.setServicio(null);
            cochera.setFechaIngreso(null);
            cochera.setFechaSalida(null);
            cochera.setPatente(null);
            cochera.setDescripcion(null);


            return cocheraRepository.save(cochera);
        }

        return null;
    }

    private Vehiculo reconstruirVehiculoDesdeCochera(Cochera cochera) {
        String tipo = cochera.getDescripcion();
        String patente = cochera.getPatente();

        Vehiculo vehiculo;
        switch (tipo) {
            case "Sedan": vehiculo = new Sedan(); break;
            case "SUV": vehiculo = new SUV(); break;
            case "Pickup": vehiculo = new Pickup(); break;
            default: throw new IllegalArgumentException("Tipo de vehículo no válido: " + tipo);
        }

        vehiculo.setPatente(patente);
        vehiculo.setDescripcion(tipo);
        return vehiculo;
    }

    public Facturacion retirarVehiculo(String patente) {
        Optional<Cochera> optionalCochera = cocheraRepository.findByPatente(patente);

        if (optionalCochera.isEmpty()) {
            return null;
        }

        Cochera cochera = optionalCochera.get();
        LocalDateTime fechaIngreso = cochera.getFechaIngreso();
        LocalDateTime fechaSalida = LocalDateTime.now();
        cochera.setFechaSalida(fechaSalida);

        Duration duracion = Duration.between(fechaIngreso, fechaSalida);
        String tipoContrato = cochera.getContrato().getNombreContrato().toLowerCase();
        BigDecimal precioBaseContrato = cochera.getContrato().getPrecioBaseCochera();
        String textoDuracion = "";
        BigDecimal precioFinalContrato = BigDecimal.ZERO;

        switch (tipoContrato) {
            case "por hora" -> {
                long horas = Math.max(2, (duracion.toMinutes() + 59) / 60);
                textoDuracion = horas + " hora/s";
                precioFinalContrato = precioBaseContrato.multiply(BigDecimal.valueOf(horas));
            }
            case "por día" -> {
                long dias = Math.max(1, (duracion.toHours() + 23) / 24);
                textoDuracion = dias + " día/s";
                precioFinalContrato = precioBaseContrato.multiply(BigDecimal.valueOf(dias));
            }
            case "por mes" -> {
                textoDuracion = "Contrato mensual";
                precioFinalContrato = precioBaseContrato;
            }
            default -> {
                textoDuracion = "Duración no disponible";
            }
        }

        // Recargo por tipo de vehículo
        Vehiculo vehiculo = reconstruirVehiculoDesdeCochera(cochera);
        BigDecimal recargo = BigDecimal.valueOf(vehiculo.obtenerRecargo());
        BigDecimal precioConRecargo = precioFinalContrato.add(precioFinalContrato.multiply(recargo));

        // Servicio
        BigDecimal costoServicio = cochera.getServicio().getCostoServicio();
        BigDecimal descuentoServicio = cochera.getContrato().getDescuentoServicio(); // entre 0.0 y 1.0
        BigDecimal servicioConDescuento = costoServicio.multiply(BigDecimal.ONE.subtract(descuentoServicio));

//        traigo el descuento para enviar a facturacion
        int descuentoServicioFacturacion = cochera.getContrato().getDescuentoServicio().intValue();

        // Total
        BigDecimal total = precioConRecargo.add(servicioConDescuento);


        // Crear facturación
        Facturacion facturacion = new Facturacion();
        facturacion.setFechaFactura(LocalDateTime.now());
        facturacion.setCodigoCochera(cochera.getCodigoCochera());
        facturacion.setPatente(patente);
        facturacion.setTipoVehiculo(vehiculo.getDescripcion());
        facturacion.setTipoContrato(cochera.getContrato().getNombreContrato());
        facturacion.setFechaIngreso(fechaIngreso);
        facturacion.setFechaEgreso(fechaSalida);
        facturacion.setDuracion(textoDuracion);
        facturacion.setServicio(cochera.getServicio().getDescripcionServicio());
        facturacion.setDescuento(descuentoServicioFacturacion);
        facturacion.setMontoTotal(total);

        facturacionRepository.save(facturacion);

        // Liberar la cochera
        liberarCochera(cochera.getCodigoCochera());

        return facturacion;
    }


}
