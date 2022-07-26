package com.desafioapirest.cliente.controller;

import com.desafioapirest.cliente.exception.ApiException;
import com.desafioapirest.cliente.model.Comprobante;
import com.desafioapirest.cliente.model.Productos;
import com.desafioapirest.cliente.service.comprobantes.ComprobanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comprobantes")
public class ComprobanteController {
    //    localhost:8080/comprabantes
    @Autowired
    ComprobanteService comprobanteService;

    @GetMapping("") //Mapeo como para dar instrucciones de lo que puede devolver este programita
    public String infoGetComprobantes(){
        return "BIENVENIDO AL SISTEMA GET DE COMPROBANTES \n" +
                "*********************************************\n" +
                "Uri's y sus funciones:\n" +
                "\n[localhost:8080/comprobantes/all/ : Podrá consultar todos los comprobantes\n" +
                "\n[localhost:8080/comprobantes/id/{id} : Reemplace el texto {id}, por el id a buscar, de existir lo verá en pantalla\n" ;
    }

    @GetMapping("/all")
    public List<Comprobante> mostrarTodos(){
        return comprobanteService.mostrarTodos();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Object> mostrarOriginalByID( @PathVariable int id) throws Exception{ // Para devolver un mensaje personalizado uso el ResponseEntity que devuelve un objeto, y este metodo ademas puede hacer un throw Exception
        Comprobante comprobante= comprobanteService.mostrarByID(id);
        if(comprobante==null){
            throw new ApiException("No se encontro ningun comprobante con el ID :"+id);
        }
        return new ResponseEntity<>(comprobante, HttpStatus.OK);
    }



}
