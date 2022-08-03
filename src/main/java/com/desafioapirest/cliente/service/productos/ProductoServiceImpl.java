package com.desafioapirest.cliente.service.productos;

import com.desafioapirest.cliente.exception.ApiException;
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

    //********************************************************************************************************************
    //*********************************             GET SERVICES                   ***************************************
    //********************************************************************************************************************

    @Override
    public List<Productos> mostrarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Productos mostrarByCODIGO(String codigo) throws Exception {
        productoscopia=productoRepository.findAll();

        for(int i =0;i<productoscopia.size();i++){
            if(productoscopia.get(i).getCodigo().equalsIgnoreCase(codigo)){
                productoAMostrar=productoscopia.get(i);
                return productoAMostrar;
            }
        }
        throw new ApiException("No se encontro ningun producto con el Codigo "+codigo);
    }

    @Override
    public Productos mostrarByID(int id) throws Exception {
        productoAMostrar= productoRepository.findById(id).orElse(null);
        if(productoAMostrar==null){throw new ApiException("No se encontro ningun producto con el ID :"+id);}
        return productoAMostrar;
    }

    @Override
    public List<Productos> buscarProductos(String descripcion) throws Exception{
        List<Productos> listaproductosAMostrar = new ArrayList<>();
        productoscopia = productoRepository.findAll();
        for(int i =0;i<productoscopia.size();i++){
            descripcionOriginal = productoscopia.get(i).getDescripcion().toLowerCase();
            if(descripcionOriginal.contains(descripcion.toLowerCase())){
                listaproductosAMostrar.add(productoscopia.get(i));
            }
        }
        if(listaproductosAMostrar.isEmpty()){throw new ApiException("No se encontro ningun producto con una descripcion que contenga :"+descripcion);}
        return listaproductosAMostrar;
    }

    //*********************************************************************************************************************
    //*********************************             POST SERVICES                   ***************************************
    //*********************************************************************************************************************

    @Override
    public Productos nuevoProducto(Productos producto) throws Exception {
        boolean codigoRepetido = buscarCodigoRepetido(producto);
        if(codigoRepetido){
            throw new ApiException("El codigo de su producto pertenece a otro ya presente en nuestra base de datos");
        }else {
            int id = calcularID();
            producto.setIdproducto(id);
            productoRepository.save(producto);
            return (producto);
        }
    }

    @Override
    public Productos actualizarProducto(Productos producto) throws Exception {
        int idfinal = productoRepository.findAll().size();
        if(producto.getIdproducto()<=idfinal && producto.getIdproducto()>0){
            producto.setCodigo(producto.getCodigo().toUpperCase());
            productoRepository.save(producto);
            return producto;
        }
        throw new ApiException("El ID de Producto no existe");
    }

    //*******************************************************************************************************************
    //*******************************             DELETE SERVICES                   *************************************
    //*******************************************************************************************************************

    @Override
    public String borrarProducto(int id) {
        String texto = "No se encontró el Producto con el id: "+id+", por lo tanto no se puede eliminar";
        if(id<1){return texto;}
        if(buscarIdProducto(id)){
            productoAMostrar=productoRepository.findById(id).orElse(null);
            texto = "El producto: ["+productoAMostrar.getDescripcion()+"], con el id: "+id+" ha sido eliminado";
            productoRepository.deleteById(id);
        }
        return texto;
    }

    //*********************************************************************************************************************
    //*********************************                  METODOS                    ***************************************
    //*********************************************************************************************************************

    public boolean buscarIdProducto(int id){
        productoscopia=productoRepository.findAll();
        for(int i=0;i<productoscopia.size();i++){
            if(productoscopia.get(i).getIdproducto()==id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public float VerifyModifCantidad(Integer idproducto, int cantidad) throws Exception{
        productoAMostrar = productoRepository.findById(idproducto).orElse(null);
        if(productoAMostrar.getStock()>=cantidad){
            int stockNuevo= productoAMostrar.getStock()-cantidad;
            productoAMostrar.setStock(stockNuevo);
            productoRepository.save(productoAMostrar);
            return cantidad*productoAMostrar.getPrecio();
        }
        throw new ApiException("Stock Insuficiente para la compra. El stock del producto :"+productoAMostrar.getDescripcion()+" es de : "+productoAMostrar.getStock());
    }


    private boolean buscarCodigoRepetido(Productos producto){
        productoscopia=productoRepository.findAll();
        for(int i=0;i<productoscopia.size();i++){
            if(productoscopia.get(i).getCodigo().equalsIgnoreCase(producto.getCodigo())) {
            return true;
            }
        }
        return false;
    }

    private int calcularID(){
        return productoRepository.findAll().size()+1;
    }
}
