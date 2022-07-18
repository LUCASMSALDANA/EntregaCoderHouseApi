package com.desafioapirest.cliente.service.linea;

import com.desafioapirest.cliente.model.Linea;
import com.desafioapirest.cliente.repository.LineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaServiceImpl implements LineaService {
    @Autowired
    LineaRepository lineaRepository;

    @Override
    public List<Linea> mostrarTodos() {
        return lineaRepository.findAll();
    }

    @Override
    public Linea mostrarByID(int id) {
        return lineaRepository.findById(id).orElse(null);
    }
}
