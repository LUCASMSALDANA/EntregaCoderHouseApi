package com.desafioapirest.cliente.service.venta;

import com.desafioapirest.cliente.model.Venta;

import java.util.List;

public interface VentaService {
    List<Venta> mostrarTodos();

    Venta mostrarByID(int id);
}
