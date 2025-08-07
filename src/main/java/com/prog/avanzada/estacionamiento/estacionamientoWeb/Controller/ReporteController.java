package com.prog.avanzada.estacionamiento.estacionamientoWeb.Controller;


import com.prog.avanzada.estacionamiento.estacionamientoWeb.Model.Facturacion;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Repository.FacturacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReporteController {
    @Autowired
    private FacturacionRepository facturacionRepository;

    @GetMapping("/reportes")
    public String mostrarReporte(Model model) {
        List<Facturacion> facturas = facturacionRepository.findAll();
        System.out.println("Cantidad de facturas: " + facturas.size());
        for (Facturacion f : facturas) {
            System.out.println(f);
        }
        model.addAttribute("facturas", facturas);
        return "reporte/listado";
    }
}
