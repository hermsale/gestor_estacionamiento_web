package com.prog.avanzada.estacionamiento.estacionamientoWeb.Service;

import com.prog.avanzada.estacionamiento.estacionamientoWeb.Model.TipoContrato;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Repository.TipoContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoContratoServiceImpl implements TipoContratoService {
    @Autowired
    private TipoContratoRepository tipoContratoRepository;

    @Override
    public List<TipoContrato> obtenerTodos() {
        return tipoContratoRepository.findAll();
    }
}
