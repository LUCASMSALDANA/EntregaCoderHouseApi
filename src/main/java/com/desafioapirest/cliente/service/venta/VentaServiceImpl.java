package com.desafioapirest.cliente.service.venta;

import com.desafioapirest.cliente.exception.ApiException;
import com.desafioapirest.cliente.model.Clientes;
import com.desafioapirest.cliente.model.Venta;
import com.desafioapirest.cliente.repository.VentaRepository;
import com.desafioapirest.cliente.service.clientes.ClienteService;
import com.desafioapirest.cliente.service.comprobantes.ComprobanteService;
import com.desafioapirest.cliente.service.productos.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {
    @Autowired
    VentaRepository ventaRepository;
    @Autowired
    ProductoService productoService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    ComprobanteService comprobanteService;


    @Override
    public List<Venta> mostrarTodos() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta mostrarByID(int id) throws Exception {
        Venta venta= ventaRepository.findById(id).orElse(null);
        if(venta==null){throw new ApiException("No se encuentra la una venta con ese ID");}
        return venta;
    }

    @Override
    public Venta nuevaVenta(Venta nueva) throws Exception {
        nueva.setIdventa(calcularID());
        if(!clienteService.buscarIDCliente(nueva.getIdcliente())){
            throw new ApiException("El ID Cliente no existe");
        }
        if(!productoService.buscarIdProducto(nueva.getIdproducto())){
            throw new ApiException("El ID Producto no existe");
        }
        if(nueva.getCantidad()<1){throw new ApiException("La cantidad a comprar debe ser mayor a 0");}
        nueva.setPreciototal(productoService.VerifyModifCantidad(nueva.getIdproducto(),nueva.getCantidad()));
        nueva.setIdcomprobante(comprobanteService.calcularID());
        return ventaRepository.save(nueva);
    }

    //*********************************************************************************************************************
    //*********************************                  METODOS                    ***************************************
    //*********************************************************************************************************************


    private int calcularID(){
        return ventaRepository.findAll().size()+1;

    }

}
