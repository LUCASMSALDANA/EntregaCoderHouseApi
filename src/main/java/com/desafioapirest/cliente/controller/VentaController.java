package com.desafioapirest.cliente.controller;

import com.desafioapirest.cliente.model.Venta;
import com.desafioapirest.cliente.service.venta.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ventas")
public class VentaController {
        //   localshost:8080/ventas
    @Autowired
    VentaService ventaService;

    //********************************************************************************************************************
    //*********************************             GET MAPPINGS                   ***************************************
    //********************************************************************************************************************
    @GetMapping("")
    public String infoGetVenta(){
        return "BIENVENIDO AL SISTEMA DE VENTAS \n" +
                "*********************************************\n" +
                "Uri's y sus funciones:\n" +
                "\n[localhost:8080/ventas/all/ : Podrá consultar por las ventas realizadas \n" +
                "\n[localhost:8080/ventas/id/{id} : Reemplace el texto {id}, por el id a buscar, de existir lo verá en pantalla\n"+
                "\n\n*** IMPORTANTE ***"+
                "\nLos unicos requisitos para crear una venta es pasar el ID Cliente, el ID producto, y la cantidad de productos.";
    }

    @GetMapping("/all")
    public List<Venta> mostrarTodos(){
        return ventaService.mostrarTodos();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Object> mostrarOriginalByID(@PathVariable int id) throws Exception{ // Para devolver un mensaje personalizado uso el ResponseEntity que devuelve un objeto, y este metodo ademas puede hacer un throw Exception
        Venta venta = ventaService.mostrarByID(id);
        return new ResponseEntity<>(venta, HttpStatus.OK);
    }

    //*********************************************************************************************************************
    //*********************************             POST MAPPINGS                   ***************************************
    //*********************************************************************************************************************

    @PostMapping("/nueva")
    public ResponseEntity<Object> nuevaVenta(@RequestBody Venta nueva)throws Exception{
        Venta ventaOk= ventaService.nuevaVenta(nueva);
        return new ResponseEntity<>(ventaOk, HttpStatus.CREATED);
    }

    //*********************************************************************************************************************
    //*********************************             DELETE MAPPINGS                   *************************************
    //*********************************************************************************************************************
    @DeleteMapping("/borrar/{id}")
    public String anularVenta(@PathVariable int id){
        String texto = ventaService.anularVenta(id);
        return texto;
    }
}
