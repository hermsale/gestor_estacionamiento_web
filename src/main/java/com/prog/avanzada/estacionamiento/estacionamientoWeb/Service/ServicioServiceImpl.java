package com.prog.avanzada.estacionamiento.estacionamientoWeb.Service;

import com.prog.avanzada.estacionamiento.estacionamientoWeb.Model.Servicio;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService {
    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public List<Servicio> obtenerTodos() {
        return servicioRepository.findAll();
    }
}
