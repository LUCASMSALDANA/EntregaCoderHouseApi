package com.desafioapirest.cliente.service.comprobantes;

import com.desafioapirest.cliente.exception.ApiException;
import com.desafioapirest.cliente.model.Comprobante;
import com.desafioapirest.cliente.model.Venta;
import com.desafioapirest.cliente.repository.ComprobanteRepository;
import com.desafioapirest.cliente.service.clientes.ClienteService;
import com.desafioapirest.cliente.service.productos.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class ComprobanteServiceImpl implements ComprobanteService{

    @Autowired
    ClienteService clienteService;
    @Autowired
    ProductoService productoService;
    @Autowired
    ComprobanteRepository comprobanteRepository;
    @Override
    public List<Comprobante> mostrarTodos() {
        return comprobanteRepository.findAll();
    }

    @Override
    public Comprobante mostrarByID(int id) throws Exception {
        Comprobante comprobante = comprobanteRepository.findById(id).orElse(null);
        if(comprobante==null){throw new ApiException("No se encontro ningun comprobante con el ID :"+id);}
            return comprobante;
    }


    @Override
    public int crearComprobante(Venta nueva) {
        Integer idComprobante = calcularID();
        Integer idVenta = nueva.getIdventa();
        Integer idCliente = nueva.getIdcliente();
        String nombreApellido = clienteService.getNombreApellido(nueva.getIdcliente());
        Integer idProducto = nueva.getIdproducto();
        String descripcionProducto = productoService.getDescripcion(nueva.getIdproducto());
        Integer cantidad = nueva.getCantidad();
        Date hoy = Date.valueOf(LocalDate.now());
        float total = nueva.getPreciototal();
        Comprobante nuevoComprobante = new Comprobante(idComprobante,idVenta,idCliente,nombreApellido,idProducto,descripcionProducto,cantidad,hoy,total);
        comprobanteRepository.save(nuevoComprobante);
        return idComprobante;
    }

    private Integer calcularID() {
        return comprobanteRepository.findAll().size()+1;
    }


}
