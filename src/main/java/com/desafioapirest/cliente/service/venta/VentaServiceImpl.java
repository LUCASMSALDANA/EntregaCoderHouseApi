package com.desafioapirest.cliente.service.venta;

import com.desafioapirest.cliente.model.Venta;
import com.desafioapirest.cliente.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {
    @Autowired
    VentaRepository ventaRepository;

    @Override
    public List<Venta> mostrarTodos() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta mostrarByID(int id) {
        return ventaRepository.findById(id).orElse(null);
    }
}
