package com.desafioapirest.cliente.service.comprobantes;

import com.desafioapirest.cliente.model.Comprobante;

import java.util.List;

public interface ComprobanteService {
    List<Comprobante> mostrarTodos();

    Comprobante mostrarByID(int id) throws Exception;

    int calcularID();
}
