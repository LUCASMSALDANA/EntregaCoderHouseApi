package com.desafioapirest.cliente.service.comprobantes;

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
    public Comprobante mostrarByID(int id) {
        return comprobanteRepository.findById(id).orElse(null);
    }

    @Override
    public Comprobante nuevoProducto(Comprobante comprobante) {
        int id = comprobanteRepository.findAll().size()+1;
        comprobante.setIdcomprobante(id);
        comprobanteRepository.save(comprobante);
        return (comprobante);
    }

    @Override
    public Comprobante actualizarProducto(Comprobante comprobante) {
        int idfinal = comprobanteRepository.findAll().size();
        if(comprobante.getIdcomprobante()<=idfinal && comprobante.getIdcomprobante()>0){
            comprobanteRepository.save(comprobante);
            return comprobante;
        }
        return null;
    }
}
