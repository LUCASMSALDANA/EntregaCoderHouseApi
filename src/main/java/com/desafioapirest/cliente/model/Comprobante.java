package com.desafioapirest.cliente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "COMPROBANTE")
public class Comprobante {
    @Id
    @Column(name = "IDCOMPROBANTE")
    private Integer idcomprobante;

    @Column(name = "IDVENTA")
    private Integer idventa;

    @Column(name = "IDCLIENTE")
    private Integer idcliente;

    @Column(name = "NOMBREAPELLIDO")
    private String nombreapellido;

    @Column(name ="IDPRODUCTO")
    private Integer idproducto;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @Column(name="CANTIDAD")
    private Integer cantidad;

    @Column(name="FECHA")
    private Date fecha;

      @Column(name="TOTAL")
    private Float total;

    //*********************
    //*   Constructores   *
    //*********************

    public Comprobante() {
    }

    public Comprobante(Integer idcomprobante, Integer idventa, Integer idcliente, String nombreapellido, Integer idproducto, String descripcion, Integer cantidad, Date fecha, Float total) {
        this.idcomprobante = idcomprobante;
        this.idventa = idventa;
        this.idcliente = idcliente;
        this.nombreapellido = nombreapellido;
        this.idproducto = idproducto;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.total = total;
    }

    //*********************
    //*Getters and Setters*
    //*********************


    public Integer getIdcomprobante() {
        return idcomprobante;
    }

    public void setIdcomprobante(Integer idcomprobante) {
        this.idcomprobante = idcomprobante;
    }

    public Integer getIdventa() {
        return idventa;
    }

    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombreapellido() {
        return nombreapellido;
    }

    public void setNombreapellido(String nombreapellido) {
        this.nombreapellido = nombreapellido;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    @Override
    public String toString(){
        return  "idcomprobante : "+this.idcomprobante+
                "\nidventa : "+this.idventa+
                "\nidcliente : "+this.idcliente+
                "\nnombreapellido : "+this.nombreapellido+
                "\nidproducto : "+this.idproducto+
                "\ndescirpcion : "+this.descripcion+
                "\ncantidad : "+this.cantidad+
                "\nfecha : "+this.fecha+
                "\ntotal : "+this.total;
    }
}
