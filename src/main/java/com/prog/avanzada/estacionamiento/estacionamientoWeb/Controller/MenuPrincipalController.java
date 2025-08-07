package com.prog.avanzada.estacionamiento.estacionamientoWeb.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuPrincipalController {

    @GetMapping("/")
    public String mostrarMenu() {
        return "menuPrincipal"; // nombre de la vista HTML (menuPrincipal.html)
    }
}