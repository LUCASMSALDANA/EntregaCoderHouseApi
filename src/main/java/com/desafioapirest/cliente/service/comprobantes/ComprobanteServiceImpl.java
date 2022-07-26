package com.desafioapirest.cliente.service.comprobantes;

import com.desafioapirest.cliente.exception.ApiException;
import com.desafioapirest.cliente.model.Comprobante;
import com.desafioapirest.cliente.repository.ComprobanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprobanteServiceImpl implements ComprobanteService{

    @Autowired
    ComprobanteRepository comprobanteRepository;
    @Override
    public List<Comprobante> mostrarTodos() {
        return comprobanteRepository.findAll();
    }

    @Override
    public Comprobante mostrarByID(int id) throws Exception {
        Comprobante comprobante = comprobanteRepository.findById(id).orElse(null);
        if(comprobante==null){throw new ApiException("No se encontro ningun comprobante con el ID :"+id);}
            return comprobante;
    }
}
