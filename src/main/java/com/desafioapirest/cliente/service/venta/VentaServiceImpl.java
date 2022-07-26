package com.desafioapirest.cliente.service.venta;

import com.desafioapirest.cliente.exception.ApiException;
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
    public Venta mostrarByID(int id) throws Exception {
        Venta venta= ventaRepository.findById(id).orElse(null);
        if(venta==null){throw new ApiException("No se encuentra la una venta con ese ID");}
        return venta;
    }
}
