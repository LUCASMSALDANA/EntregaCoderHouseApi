package com.desafioapirest.cliente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "PRODUCTOS")
public class Productos {
    @Id
    @Column(name ="IDPRODUCTO")
    private Integer idproducto;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @Column(name="PRECIO")
    private Float precio;

    @Column(name="STOCK")
    private Integer stock;

    @Column(name="CODIGO")
    private String codigo;

    //*********************
    //*   Constructores   *
    //*********************
    public Productos() {
    }

    public Productos(Integer idproducto, String descripcion, Float precio, Integer stock, String codigo) {
        this.idproducto = idproducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.codigo = codigo;
    }

    //*********************
    //*Getters and Setters*
    //*********************
    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
