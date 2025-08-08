package com.prog.avanzada.estacionamiento.estacionamientoWeb.Controller;

import com.prog.avanzada.estacionamiento.estacionamientoWeb.Model.Cochera;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Model.Servicio;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Model.TipoContrato;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Service.CocheraService;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Service.ServicioService;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Service.TipoContratoService;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Service.TipoContratoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/ingresoVehiculo")
public class IngresoVehiculoController {

    @Autowired
    private CocheraService cocheraService;
    @Autowired
    private TipoContratoService tipoContratoService;
    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public String mostrarFormularioIngreso(Model model) {
        Cochera cochera = new Cochera();


        cochera.setServicio(new Servicio());
        cochera.setContrato(new TipoContrato());

        model.addAttribute("cochera", cochera);
        model.addAttribute("contratos", tipoContratoService.obtenerTodos());
        model.addAttribute("servicios", servicioService.obtenerTodos());
        model.addAttribute("cocheras", cocheraService.getAll());

        return "ingreso/formulario";
    }

    @PostMapping
    public String procesarIngreso(@ModelAttribute Cochera cochera, Model model) {

        if (cochera.getContrato() == null) {
            System.out.println("se crea el tipo de contrato");
            cochera.setContrato(new TipoContrato());
        }
        if (cochera.getServicio() == null) {
            System.out.println("se crea el servicio");
            cochera.setServicio(new Servicio());
        }


        System.out.println(">>> DEBUG: cochera.getCodigoCochera(): " + cochera.getCodigoCochera());
        cochera.setIdCochera(cochera.getCodigoCochera());
        System.out.println(">>> DEBUG: cochera.getIdCochera(): " + cochera.getIdCochera());

        System.out.println(">>> DEBUG: cochera.getPatente(): " + cochera.getPatente());
        System.out.println(">>> DEBUG: cochera.descripcion (): " + cochera.getDescripcion());

        if (cochera.getContrato() != null) {
            System.out.println(">>> DEBUG: contrato.idTipoContrato = " + cochera.getContrato().getNombreContrato());
        } else {
            System.out.println(">>> DEBUG: contrato es NULL");
        }

        if (cochera.getServicio() != null) {
            System.out.println(">>> DEBUG: servicio.idServicio = " + cochera.getServicio().getDescripcionServicio());
        } else {
            System.out.println(">>> DEBUG: servicio es NULL");
        }
        cocheraService.ingresarVehiculo(cochera);

        model.addAttribute("mensaje", "Vehículo ingresado correctamente");


        return "redirect:/";
    }
}