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
        List<String> error= new ArrayList<>();
        String stockInsuficiente ="";
        int pos=0;
        nueva.setIdventa(calcularID());
        if(!clienteService.buscarIDCliente(nueva.getIdcliente())){
            error.add("El ID Cliente no existe");
            pos++;
        }
        if(!productoService.buscarIdProducto(nueva.getIdproducto())){
            throw new ApiException("El ID Producto no existe");
        }
        if(nueva.getCantidad()<1) {
            error.add("La cantidad a comprar debe ser mayor a 0");
            pos++;
        }else {
            stockInsuficiente = productoService.VerifCantidad(nueva.getIdproducto(), nueva.getCantidad());
            if (stockInsuficiente.contains("Stock Insuficiente")) {
                error.add(stockInsuficiente);
            }
        }
            if(!error.isEmpty()){
                String textoError= armarTextoError(error);
                throw  new ApiException(textoError);
            }
            nueva.setPreciototal(Float.valueOf(stockInsuficiente));
            nueva.setIdcomprobante(comprobanteService.crearComprobante(nueva));
            productoService.modifStock(nueva.getIdproducto(),nueva.getCantidad());
        return ventaRepository.save(nueva);
    }


    //****************************************************************************************************************
    //*******************************                  METODOS                    ************************************
    //****************************************************************************************************************


    private int calcularID(){
        return ventaRepository.findAll().size()+1;

    }

    private String armarTextoError(List<String> error) {
        String textoError="";
        for(String err: error){
            textoError+=err+". ";
        }
        return textoError;
    }


}
