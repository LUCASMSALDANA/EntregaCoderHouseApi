package com.desafioapirest.cliente.dto;

// Esta clase es una copia casi exacta de Clientes, solo que en lugar de fecha de nacimiento
//voy a guardar la edad en formato int... Con esto  puedo duplicar cada fila de la tabla CLIENTES, pero
// con la diferencia que voy a guardar la edad de cada cliente. La edad la calculo en la capa de Service

public class ClientesDTO {

    private int idcliente;
    private int dni;
    private String nombre;
    private String apellido;
    private int edad;


    //*********************
    //*   Constructores   *
    //*********************
    public ClientesDTO(){
    }

    public ClientesDTO(int idcliente,int dni, String nombre, String apellido, int edad) {
        this.idcliente = idcliente;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    //*********************
    //*Getters and Setters*
    //*********************


    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}
