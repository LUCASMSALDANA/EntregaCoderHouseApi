package com.desafioapirest.cliente.controller;

import com.desafioapirest.cliente.exception.ApiException;
import com.desafioapirest.cliente.model.Clientes;
import com.desafioapirest.cliente.dto.ClientesDTO;
import com.desafioapirest.cliente.service.clientes.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController    // Con esto le indico a SpringBoot q esta es mi clase Rest Controller
@RequestMapping("clientes") // Aqui la mapeo, este es su endpoint, es decir en Postman la llamo poniendo: localhost:8080/clientes, pero no devolveria nada en este caso
public class ClienteController {

    @Autowired  // Aqui inyecto lo que va a ser mi clase de Service (Es decir donde estará la lógica de cada metodo que llame)
    ClienteService clienteService;

    @GetMapping("") //Mapeo como para dar instrucciones de lo que puede devolver este programita
    public String infoGetClientes(){
        return "BIENVENIDO AL SISTEMA GET DE CLIENTES \n" +
                "*********************************************\n" +
                "Uri's y sus funciones:\n\n" +
                "[localhost:8080/clientes/all] : Podrá consultar todos los clientes y su fecha de nacimiento" +
                "\n[localhost:8080/clientes/alledad] : Podrá consultar todos los clientes y su edad" +
                "\n[localhost:8080/clientes/dni/{dni} : Reemplace el texto {dni}, por el dni a buscar, de existir lo verá en pantalla|" +
                "\n[localhost:8080/clientes/id/{id} : Reemplace el texto {id}, por el id a buscar, de existir lo verá en pantalla" +
                "\n[localhost:8080/clientes/idedad/{id} : Reemplace el texto{id} por el id a buscar, de existir vera ese cliente con su edad ";
    }

    @GetMapping("/all") //Mapeo, y agrego un endpoint para el siguiente metodo
    public List<Clientes> mostrarTodos(){
        return clienteService.mostrarTodos();
    }

    @GetMapping("/alledad") // Mapeo, y agrego un endpoint para el siguiente metodo
    public List<ClientesDTO> mostrarTodosDNI(){
        return clienteService.mostrarClientesEdad();
    }

    @GetMapping("/dni/{dni}")//Mapeo, y agrego un endpoint para el siguiente metodo
    public ResponseEntity<Object> mostrarbyDNI( @PathVariable int dni) throws Exception { // Para devolver un mensaje personalizado uso el ResponseEntity que devuelve un objeto, y este metodo ademas puede hacer un throw Exception
        ClientesDTO cliente = clienteService.mostrarByDNI(dni);
        if(cliente==null){
            throw new ApiException("No se encuentra el DNI : "+dni);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> mostrarOriginalByID( @PathVariable int id) throws Exception { // Para devolver un mensaje personalizado uso el ResponseEntity que devuelve un objeto, y este metodo ademas puede hacer un throw Exception
        Clientes cliente = clienteService.mostrarOriginalByID(id);
        if(cliente==null){
            throw new ApiException("No se encontro ningun cliente con el ID :"+id);
        }else{
            return new ResponseEntity<>(cliente,HttpStatus.OK);
        }
    }

    @GetMapping("/idedad/{id}")
    public ResponseEntity<Object> mostrarEdadByID(@PathVariable int id) throws Exception { // Para devolver un mensaje personalizado uso el ResponseEntity que devuelve un objeto, y este metodo ademas puede hacer un throw Exception
        ClientesDTO cliente = clienteService.mostrarEdadByID(id);
        if(cliente==null){
            throw new ApiException("No se encontro ningun cliente con el ID :"+id);
        }
        return new ResponseEntity<>(cliente,HttpStatus.OK);
    }


 //*********************************************************************************************************************
 //*********************************             POST MAPPINGS                   ***************************************
 //*********************************************************************************************************************


    @PostMapping("clientes") //Mapeo como para dar instrucciones de lo que puede devolver este programita
    public String infoPostClientes(){
        return "BIENVENIDO AL SISTEMA DE ABM DE CLIENTES \n" +
                "*********************************************\n" +
                "Uri's y sus funciones:\n\n" +
                "[localhost:8080/clientes/crear] : Podrá crear un cliente nuevo" +
                "\n[localhost:8080/clientes/actualizar : Podrá actualizar un cliente";
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> nuevoCliente(@RequestBody Clientes cliente) throws Exception  {
        ClientesDTO nuevoCliente= clienteService.nuevoCliente(cliente);
        if(nuevoCliente==null){
            throw new ApiException("No se puede crear el Cliente, ya que ese DNI se encuentra en nuestra base de datos");
        }
        return new ResponseEntity<>(nuevoCliente,HttpStatus.OK);
    }

    @PostMapping("/actualizar")
    public ResponseEntity<Object> actualizarCliente(@RequestBody Clientes cliente) throws ApiException {
        ClientesDTO nuevoCliente = clienteService.actualizarCliente(cliente);
        if(nuevoCliente==null){
            throw new ApiException("El ID de Cliente no existe");
        }
        return new ResponseEntity<>(nuevoCliente,HttpStatus.OK);
    }

}
