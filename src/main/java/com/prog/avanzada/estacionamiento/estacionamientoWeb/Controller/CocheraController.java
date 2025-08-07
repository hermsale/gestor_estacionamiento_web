package com.prog.avanzada.estacionamiento.estacionamientoWeb.Controller;


import com.prog.avanzada.estacionamiento.estacionamientoWeb.Model.Cochera;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Service.CocheraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cocheras")
public class CocheraController {

    @Autowired
    private CocheraService cocheraService;

    @GetMapping
    public List<Cochera> getAll() {
        return cocheraService.getAll();
    }

    @GetMapping("/codigo/{codigo}")
    public Cochera getByCodigo(@PathVariable int codigo) {
        return cocheraService.findByCodigo(codigo).orElse(null);
    }

    @GetMapping("/patente/{patente}")
    public Cochera getByPatente(@PathVariable String patente) {
        return cocheraService.findByPatente(patente).orElse(null);
    }

    @PutMapping("/ingresar")
    public Cochera ingresarVehiculo(@RequestBody Cochera cochera) {
        return cocheraService.ingresarVehiculo(cochera);
    }

    @PutMapping("/liberar/{codigo}")
    public Cochera liberarCochera(@PathVariable int codigo) {
        return cocheraService.liberarCochera(codigo);
    }
}