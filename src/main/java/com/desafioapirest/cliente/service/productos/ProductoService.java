package com.desafioapirest.cliente.service.productos;

import com.desafioapirest.cliente.model.Productos;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductoService {

    List<Productos> mostrarTodos();
    Productos mostrarByCODIGO(String codigo);
    Productos mostrarByID(int id);
    List<Productos> buscarProductos(String descripcion);
    Productos nuevoProducto(Productos producto);
    Productos actualizarProducto(Productos producto);
}
