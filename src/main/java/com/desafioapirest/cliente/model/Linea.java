package com.desafioapirest.cliente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Linea")
public class Linea {
    @Id
    @Column(name = "IDLINEA")
    private Integer idlinea;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "CANTIDAD")
    private int cantidad;

    @Column(name = "PRECIO")
    private Float precio;

    @Column(name = "IDCOMPROBANTE")
    private Integer idcomprobante;

    @Column(name = "IDPRODUCTO")
    private Integer idproducto;

    //*********************
    //*   Constructores   *
    //*********************


    public Linea() {
    }

    public Linea(Integer idlinea, String descripcion, int cantidad, Float precio, Integer idcomprobante, Integer idproducto) {
        this.idlinea = idlinea;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.idcomprobante = idcomprobante;
        this.idproducto = idproducto;
    }
    //*********************
    //*Getters and Setters*
    //*********************


    public Integer getIdlinea() {
        return idlinea;
    }

    public void setIdlinea(Integer idlinea) {
        this.idlinea = idlinea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getIdcomprobante() {
        return idcomprobante;
    }

    public void setIdcomprobante(Integer idcomprobante) {
        this.idcomprobante = idcomprobante;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }
}



