package com.prog.avanzada.estacionamiento.estacionamientoWeb.Controller;

import com.prog.avanzada.estacionamiento.estacionamientoWeb.Model.*;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Repository.FacturacionRepository;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Service.CocheraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping("/retiroVehiculo")
public class RetiroVehiculoController {


    @Autowired
    private FacturacionRepository facturacionRepository;

    private final CocheraService cocheraService;

    public RetiroVehiculoController(CocheraService cocheraService) {
        this.cocheraService = cocheraService;
    }

    @GetMapping
    public String mostrarFormularioRetiro() {
        return "retiro/busqueda"; // formulario donde se ingresa la patente
    }

    @PostMapping
    public String procesarRetiro(@RequestParam("patente") String patente, Model model) {

        Optional<Cochera> cocheraPatente = cocheraService.findByPatente(patente);

        if (cocheraPatente.isEmpty()) {
            model.addAttribute("error", "No se encontró una cochera con la patente ingresada.");
            return "retiro/busqueda";
        }

        Cochera cochera = cocheraPatente.get();

//        Creo un vehiculo para completar sus datos
        Vehiculo vehiculo;
        switch (cochera.getDescripcion().toLowerCase()) {
            case "sedan" -> vehiculo = new Sedan();
            case "suv" -> vehiculo = new SUV();
            case "pickup" -> vehiculo = new Pickup();
            default -> vehiculo = new Sedan(); // Valor por defecto o lanzar excepción
        }

        vehiculo.setPatente(cochera.getPatente());
        vehiculo.setDescripcion(cochera.getDescripcion());

        cochera.setVehiculo(vehiculo);
        double recargoCochera = vehiculo.obtenerRecargo();

        // Fechas
        LocalDateTime fechaIngreso = cochera.getFechaIngreso();
        LocalDateTime fechaEgreso = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Duración
        Duration duracion = Duration.between(fechaIngreso, fechaEgreso);

        // Contrato
        String tipoContrato = cochera.getContrato().getNombreContrato();
        double precioBaseContrato = cochera.getContrato().getPrecioBaseCochera().doubleValue();
        String textoDuracion;
        double precioFinal = 0;

        switch (tipoContrato.toLowerCase()) {
            case "por hora" -> {
                long horas = Math.max(2, (duracion.toMinutes() + 59) / 60);
                textoDuracion = horas + " hora/s";
                precioFinal = precioBaseContrato * horas;
            }
            case "por día" -> {
                long dias = Math.max(1, (duracion.toHours() + 23) / 24);
                textoDuracion = dias + " día/s";
                precioFinal = precioBaseContrato * dias;
            }
            case "por mes" -> {
                textoDuracion = "Contrato mensual";
                precioFinal = precioBaseContrato;
            }
            default -> {
                textoDuracion = "No disponible";
            }
        }

        // Servicios y descuentos
        String servicioDescripcion = cochera.getServicio().getDescripcionServicio();
        double costoServicio = cochera.getServicio().getCostoServicio().doubleValue();
        int descuentoServicio = cochera.getContrato().getDescuentoServicio().intValue();


        // Aplicar recargo y descuento
        double precioConRecargo = precioFinal + (precioFinal * recargoCochera);
        double precioServicioConDescuento = costoServicio * (1 - descuentoServicio / 100.0);
        double precioTotal = precioConRecargo + precioServicioConDescuento;


        Facturacion factura = new Facturacion();
        factura.setFechaFactura(LocalDateTime.now());
        factura.setPatente(vehiculo.getPatente());
        factura.setTipoVehiculo(vehiculo.getDescripcion()); // si tenés getter
        factura.setCodigoCochera(cochera.getCodigoCochera());
        factura.setTipoContrato(tipoContrato);
        factura.setFechaIngreso(fechaIngreso);
        factura.setFechaEgreso(fechaEgreso);
        factura.setDuracion(textoDuracion);
        factura.setServicio(servicioDescripcion.equalsIgnoreCase("Ninguno") ? "Sin servicio contratado" : servicioDescripcion);
        factura.setDescuento(descuentoServicio);
        factura.setMontoTotal(BigDecimal.valueOf(precioTotal)); // BigDecimal!

        model.addAttribute("factura", factura);

        facturacionRepository.save(factura);
        cocheraService.liberarCochera(cochera.getCodigoCochera());


        return "retiro/busqueda"; // Muestra en el mismo formulario



    }
}