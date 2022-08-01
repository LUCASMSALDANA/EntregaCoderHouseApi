package com.desafioapirest.cliente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VENTA")
public class Venta {
    @Id
    @Column(name = "IDVENTA")
    private Integer idventa;

    @Column(name = "IDPRODUCTO")
    private Integer idproducto;

    @Column(name = "CANTIDAD")
    private int cantidad;

    @Column(name = "PRECIOTOTAL")
    private Float preciototal;

    @Column(name = "IDCLIENTE")
    private int idcliente;

    @Column(name = "IDCOMPROBANTE")
    private Integer idcomprobante;


    //*********************
    //*   Constructores   *
    //*********************


    public Venta() {
    }

    public Venta(Integer idventa, Integer idproducto, int cantidad, Float preciototal, int idcliente, Integer idcomprobante) {
        this.idventa = idventa;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.preciototal = preciototal;
        this.idcliente = idcliente;
        this.idcomprobante = idcomprobante;
    }

    //*********************
    //*Getters and Setters*
    //*********************


    public Integer getIdventa() {
        return idventa;
    }

    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(Float preciototal) {
        this.preciototal = preciototal;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdcomprobante() {
        return idcomprobante;
    }

    public void setIdcomprobante(Integer idcomprobante) {
        this.idcomprobante = idcomprobante;
    }

    @Override
    public String toString(){
        return "idventa: "+this.idventa+
                "\nidproducto: "+this.idproducto+
                "\ncantidad: "+this.cantidad+
                "\npreciototal "+this.preciototal+
                "\nidcliente "+this.idcliente+
                "\nidcomprobante "+this.idcomprobante;

    }

}