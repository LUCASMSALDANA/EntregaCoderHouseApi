package com.desafioapirest.cliente.service.linea;

import com.desafioapirest.cliente.model.Linea;

import java.util.List;

public interface LineaService {
    List<Linea> mostrarTodos();

    Linea mostrarByID(int id);
}
