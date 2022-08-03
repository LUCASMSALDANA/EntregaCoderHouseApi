package com.desafioapirest.cliente.service.clientes;

import com.desafioapirest.cliente.exception.ApiException;
import com.desafioapirest.cliente.model.Clientes;
import com.desafioapirest.cliente.dto.ClientesDTO;

import java.util.List;

public interface ClienteService {
    //Interfaz de la clase Service, aqui se declaran los metodos que luego implementare
    // en la clase correspondiente

    List<Clientes> mostrarTodos();

    List<ClientesDTO> mostrarClientesEdad();  //este metodo va a devolver la lista de clientes
    // Es importante aclarar que ClientesDTO, es una clase nueva que cree para que pueda guardar la edad
    // en tipo int, ya que lo que esta guardado en mi base de datos es tipo Date..
    // Basicamente ClientesDTO es una copia de Clientes, solo que en Clientes guardo la fecha de nacimiento
    // mientras que en Cleintes DTO voy a guardar la edad luego de ejecutar cierta logica.

    ClientesDTO mostrarByDNI(int dni) throws Exception; // Este metodo devuelve un cliente segun dni
    Clientes mostrarOriginalByID(Integer idcliente) throws Exception;
    ClientesDTO mostrarEdadByID(Integer idcliente) throws Exception;

    ClientesDTO nuevoCliente(Clientes cliente) throws ApiException, Exception;
    ClientesDTO actualizarCliente(Clientes cliente) throws Exception;
    String borrarCliente(int id);

    boolean buscarIDCliente(int idcliente);


}
