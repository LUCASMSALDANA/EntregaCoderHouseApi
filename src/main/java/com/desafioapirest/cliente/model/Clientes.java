package com.desafioapirest.cliente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.time.Instant;

//Esta clase sera mi Entity de la tabla clientes
@Entity   //Le aviso a SpringBoot que esta es una clase entity
@Table(name="CLIENTES")  // Y que se refiere a la tabla CLIENTES
public class Clientes {

    @Id
    @Column(name = "IDCLIENTE")
    private Integer idcliente;

      //Le digo a Springboot q esta variable va a ser mi ID
    @Column(name="DNI") //Le digo a Springboot que la variable responde a la columna DNI
    private Integer dni; // Esta variable q estoy creando, es el id y responde a la coumna ID

    @Column(name="NOMBRE") //La siguiente variable responde a la columna NOMBRE
    private String nombre;

    @Column(name="APELLIDO")//La siguiente variable responde a la columna APELLIDO
    private String apellido;

    @Column(name="FECHANACIMIENTO") //La siguiente variable responde a la columna FECHANACIMIENTO
    private Date fechanacimiento;

    //Es importante que los tipos de datos de cada cada variable sea igual a los datos que corresponda para cada columna
    // Ej.: si en la table tengo un dato INT, aqui deberia ser Integer, o si es VARCHAR deberia ser String

    //*********************
    //*   Constructores   *
    //*********************
    public Clientes(){
    }

    public Clientes(Integer idcliente,Integer dni, String nombre, String apellido, Date fechanacimiento) {
        this.idcliente = idcliente;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanacimiento = fechanacimiento;
    }

    //*********************
    //*Getters and Setters*
    //*********************


    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

}
