package com.desafioapirest.cliente.service.productos;

import com.desafioapirest.cliente.dto.ClientesDTO;
import com.desafioapirest.cliente.model.Clientes;
import com.desafioapirest.cliente.model.Productos;
import com.desafioapirest.cliente.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    ProductoRepository productoRepository;

    List<Productos> productoscopia;
    Productos productoAMostrar = new Productos();
    String descripcionOriginal;

    //*********************************************************************************************************************
    //*********************************             REST SERVICES                   ***************************************
    //*********************************************************************************************************************

    @Override
    public List<Productos> mostrarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Productos mostrarByCODIGO(String codigo) {
        productoscopia=productoRepository.findAll();

        for(int i =0;i<productoscopia.size();i++){
            if(productoscopia.get(i).getCodigo().equalsIgnoreCase(codigo)){
                productoAMostrar=productoscopia.get(i);
                return productoAMostrar;
            }
        }
        return null;
    }

    @Override
    public Productos mostrarByID(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Productos> buscarProductos(String descripcion) {
        List<Productos> listaproductosAMostrar = new ArrayList<>();
        productoscopia = productoRepository.findAll();
        for(int i =0;i<productoscopia.size();i++){
            descripcionOriginal = productoscopia.get(i).getDescripcion().toLowerCase();
            if(descripcionOriginal.contains(descripcion.toLowerCase())){
                listaproductosAMostrar.add(productoscopia.get(i));
            }
        }
        return listaproductosAMostrar;
    }

    //*********************************************************************************************************************
    //*********************************             POST SERVICES                   ***************************************
    //*********************************************************************************************************************

    @Override
    public Productos nuevoProducto(Productos producto) {
        boolean codigoRepetido = buscarCodigoRepetido(producto);
        if(codigoRepetido){
            return null;
        }else {
            int id = calcularID();
            producto.setIdproducto(id);
            productoRepository.save(producto);
            return (producto);
        }
    }

    @Override
    public Productos actualizarProducto(Productos producto) {
        int idfinal = productoRepository.findAll().size();
        if(producto.getIdproducto()<=idfinal && producto.getIdproducto()>0){
            productoRepository.save(producto);
            return producto;
        }
        return null;
    }

    //*********************************************************************************************************************
    //*********************************                  METODOS                    ***************************************
    //*********************************************************************************************************************

    private boolean buscarCodigoRepetido(Productos producto){
        productoscopia=productoRepository.findAll();
        for(int i=0;i<productoscopia.size();i++){
            if(productoscopia.get(i).getCodigo().equals(producto.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    private int calcularID(){
        return productoRepository.findAll().size()+1;
    }
}
