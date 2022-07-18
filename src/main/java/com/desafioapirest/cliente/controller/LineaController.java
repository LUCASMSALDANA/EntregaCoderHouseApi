package com.desafioapirest.cliente.controller;

import com.desafioapirest.cliente.exception.ApiException;
import com.desafioapirest.cliente.model.Comprobante;
import com.desafioapirest.cliente.model.Linea;
import com.desafioapirest.cliente.service.linea.LineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("linea")
public class LineaController {

    @Autowired
    LineaService lineaService;

    @GetMapping("")
    public String infoGetLinea(){
        return "BIENVENIDO AL SISTEMA DE GET DE LINEA \n" +
                "*********************************************\n" +
                "Uri's y sus funciones:\n" +
                "\n[localhost:8080/linea/all/ : Podrá consultar todos las lineas\n" +
                "\n[localhost:8080/linea/id/{id} : Reemplace el texto {id}, por el id a buscar, de existir lo verá en pantalla\n" ;
    }

    @GetMapping("/all")
    public List<Linea> mostrarTodos(){
        return lineaService.mostrarTodos();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Object> mostrarOriginalByID(@PathVariable int id) throws Exception{ // Para devolver un mensaje personalizado uso el ResponseEntity que devuelve un objeto, y este metodo ademas puede hacer un throw Exception
        Linea linea= lineaService.mostrarByID(id);
        if(linea==null){
            throw new ApiException("No se encontro ninguna linea con el ID : "+id);
        }
        return new ResponseEntity<>(linea, HttpStatus.OK);
    }
}
