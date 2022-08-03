package com.desafioapirest.cliente.service.comprobantes;

import com.desafioapirest.cliente.model.Comprobante;
import com.desafioapirest.cliente.model.Venta;

import java.util.List;

public interface ComprobanteService {
    List<Comprobante> mostrarTodos();

    Comprobante mostrarByID(int id) throws Exception;

    int crearComprobante(Venta nueva);
}
