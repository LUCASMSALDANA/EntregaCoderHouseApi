package com.desafioapirest.cliente.controller;

import com.desafioapirest.cliente.model.Productos;
import com.desafioapirest.cliente.service.productos.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productos")
public class ProductoController {

    @Autowired  // Aqui inyecto lo que va a ser mi clase de Service (Es decir donde estará la lógica de cada metodo que llame)
    ProductoService productoService;

    //********************************************************************************************************************
    //*********************************             GET MAPPINGS                   ***************************************
    //********************************************************************************************************************

    @GetMapping("") //Mapeo como para dar instrucciones de lo que puede devolver este programita
    public String infoGetProductos(){
        return "BIENVENIDO AL SISTEMA GET DE PRODUCTOS \n" +
                "*********************************************\n" +
                "Uri's y sus funciones:\n" +
                "\n[localhost:8080/productos/all/ : Podrá consultar todos los productos\n" +
                "\n[localhost:8080/productos/codigo/{codigo} : Reemplace el texto {codigo}, por el codigo a buscar, de existir lo verá en pantalla\n" +
                "\n[localhost:8080/productos/id/{id} : Reemplace el texto {id}, por el id a buscar, de existir lo verá en pantalla\n" +
                "\n[localhost:8080/productos/buscar/{descripcion} : Reemplace el texto {descripcion}, por el producto a buscar. Devuelve una lista de todos los productos que contengan ese texto\n" ;
    }

    @GetMapping("/all")
    public List<Productos> mostrarTodos(){
        return productoService.mostrarTodos();
    }


    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Object> mostrarByDCODIGO( @PathVariable String codigo) throws Exception{ // Para devolver un mensaje personalizado uso el ResponseEntity que devuelve un objeto, y este metodo ademas puede hacer un throw Exception
        Productos producto= productoService.mostrarByCODIGO(codigo);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> mostrarOriginalByID( @PathVariable int id) throws Exception{ // Para devolver un mensaje personalizado uso el ResponseEntity que devuelve un objeto, y este metodo ademas puede hacer un throw Exception
        Productos producto= productoService.mostrarByID(id);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @GetMapping("buscar/{descripcion}")
    public ResponseEntity<Object> buscarProductos(@PathVariable String descripcion) throws Exception { // Para devolver un mensaje personalizado uso el ResponseEntity que devuelve un objeto, y este metodo ademas puede hacer un throw Exception
        List<Productos> producto= productoService.buscarProductos(descripcion);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }


    //*********************************************************************************************************************
    //*********************************             POST MAPPINGS                   ***************************************
    //*********************************************************************************************************************


    @PostMapping("") //Mapeo como para dar instrucciones de lo que puede devolver este programita
    public String infoPostProductos(){
        return "BIENVENIDO AL SISTEMA DE ABM DE PRODUCTOS \n" +
                "*********************************************\n" +
                "Uri's y sus funciones:\n" +
                "\n[localhost:8080/productos/crear] : Podrá crear un Producto nuevo\n" +
                "\n[localhost:8080/productos/actualizar : Podrá actualizar un Producto";
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> nuevoProducto(@RequestBody Productos producto) throws Exception {
        producto= productoService.nuevoProducto(producto);
        return new ResponseEntity<>(producto,HttpStatus.OK);
    }

    @PostMapping("/actualizar")
    public ResponseEntity<Object> actualizarProducto(@RequestBody Productos producto) throws Exception {
        producto= productoService.actualizarProducto(producto);
        return new ResponseEntity<>(producto,HttpStatus.OK);
    }

    //*********************************************************************************************************************
    //*********************************             DELETE MAPPINGS                   *************************************
    //*********************************************************************************************************************

    @DeleteMapping("/borrar/{id}")
    public String borrarProducto(@PathVariable int id){
        String texto = productoService.borrarProducto(id);
        return texto;
    }

}
