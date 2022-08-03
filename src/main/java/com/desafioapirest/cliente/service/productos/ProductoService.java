package com.desafioapirest.cliente.service.productos;

import com.desafioapirest.cliente.model.Productos;

import java.util.List;

public interface ProductoService {

    List<Productos> mostrarTodos();
    Productos mostrarByCODIGO(String codigo) throws Exception;
    Productos mostrarByID(int id) throws Exception;
    List<Productos> buscarProductos(String descripcion) throws Exception;
    Productos nuevoProducto(Productos producto) throws Exception;
    Productos actualizarProducto(Productos producto) throws Exception;

    String borrarProducto(int id);
    boolean buscarIdProducto(int id);

    float VerifyModifCantidad(Integer idproducto, int cantidad) throws Exception;
}
