package com.desafioapirest.cliente.service.clientes;

import com.desafioapirest.cliente.model.Clientes;
import com.desafioapirest.cliente.dto.ClientesDTO;
import com.desafioapirest.cliente.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Esta clase implementará la interfaz service, y será la que tendra la lógica de cada metodo al que llamo
//en mi capa Controller

@Service
public class ClienteServImpl implements ClienteService{
    @Autowired
    ClientesRepository clientesRepository;
    List<Clientes> clientescopia;



    int finalLista, anio,mes,dia;
    Clientes elementocliente;
    ClientesDTO amostrar;
    Integer edad;
    Date fechanacimiento;
    String fechanacimientoString;

    //*********************************************************************************************************************
    //*********************************             REST SERVICES                   ***************************************
    //*********************************************************************************************************************

    @Override
    public List<Clientes> mostrarTodos() {
        return clientesRepository.findAll();
    }

    @Override
    public List<ClientesDTO> mostrarClientesEdad() {
        List<ClientesDTO> listaParaMostrar = new ArrayList<>();
        clientescopia=clientesRepository.findAll(); //utilizo el metodo findAll, para traer toda la lista de clientes de mi BBDD
        finalLista=clientescopia.size(); //Utilizo .size, para saber cuantos elementos trae la lista, y guardo este numero
        for(int i=0; i<=finalLista-1;i++){ // recorro mi list con un for, y utilizo la variable que cree recien para saber donde terminar
           elementocliente=clientescopia.get(i); //Guardo el primer objeto CLIENTE de mi lista, en la variable elementocliente
           edad = calcularEdad(elementocliente); // Voy a utilizar mi metodo para calcular la edad
           amostrar= new ClientesDTO(elementocliente.getIdcliente(),elementocliente.getDni(), elementocliente.getNombre(), elementocliente.getApellido(), edad); // Aqui estoy creando una copia de mi cliente, solo que este objeto amostrar, va a guardar la edad en lugar de fecha de nacimiento
           listaParaMostrar.add(amostrar); //agrego el cliente a mostrar, a una lista de tipo CLIENTESDTO, que va a ser la lista q se va a mostrar como JSON
        }
        return listaParaMostrar;
    }

    @Override
    public ClientesDTO mostrarByDNI(int dni) {
        clientescopia=clientesRepository.findAll();
        for(int i =0;i<clientescopia.size();i++){
            if(clientescopia.get(i).getDni().equals(dni)){
                elementocliente=clientescopia.get(i);
                edad = calcularEdad(elementocliente);
                amostrar= new ClientesDTO(elementocliente.getIdcliente(),elementocliente.getDni(), elementocliente.getNombre(), elementocliente.getApellido(), edad);
                return amostrar;
            }
        }
        return null;
    }

    @Override
    public Clientes mostrarOriginalByID(Integer idcliente) {
        return clientesRepository.findById(idcliente).orElse(null);
    }

    @Override
    public ClientesDTO mostrarEdadByID(Integer idcliente) {
        elementocliente=clientesRepository.findById(idcliente).orElse(null); //utilizo el repository para buscar por dni
        if(elementocliente==null){
            return null;
        }
        edad=calcularEdad(elementocliente); // utilizo el metodo para calcular la edad
        amostrar= new ClientesDTO(elementocliente.getIdcliente(), elementocliente.getDni(), elementocliente.getNombre(), elementocliente.getApellido(), edad); // Creo un objeto tipo CLIENTEDTO para mostrar
        return amostrar;
    }

    //*********************************************************************************************************************
    //*********************************             POST SERVICES                   ***************************************
    //*********************************************************************************************************************

    public ClientesDTO nuevoCliente(Clientes cliente) {
        boolean dniRepetido = buscarDniRepetido(cliente);
        if(dniRepetido){
            return null;
        }else {
            int id = calcularID();
            cliente.setIdcliente(id);
            clientesRepository.save(cliente);
            edad = calcularEdad(cliente);
            amostrar = new ClientesDTO(id, cliente.getDni(), cliente.getNombre(), cliente.getApellido(), edad);
            return (amostrar);
        }
    }

    @Override
    public ClientesDTO actualizarCliente(Clientes cliente) {
        int idfinal = clientesRepository.findAll().size();
        if(cliente.getIdcliente()<=idfinal && cliente.getIdcliente()>0){
            clientesRepository.save(cliente);
            edad=calcularEdad(cliente);
            amostrar = new ClientesDTO(cliente.getIdcliente(), cliente.getDni(), cliente.getNombre(), cliente.getApellido(), edad);
          return amostrar;
        }
        return null;
    }


    //*********************************************************************************************************************
    //*********************************                  METODOS                    ***************************************
    //*********************************************************************************************************************
    private int calcularEdad (Clientes cliente){  //Este va a ser el método que devuelva la edad
        fechanacimiento=cliente.getFechanacimiento(); // Guardo la fecha de nacimiento del cliente en una variable tipo Date
        fechanacimientoString=fechanacimiento.toString(); // Esa variable que guarde antes la convierto en String y la guardo en otra variable
        anio=Integer.parseInt(fechanacimientoString.substring(0,4)); // Utilizo el metodo substring, para quedarme solo con el año y lo convierto a un Int
        mes= Integer.parseInt(fechanacimientoString.substring(5,7)); // Utilizo el metodo substring, para quedarme solo con el mes y lo convierto a un Int
        dia= Integer.parseInt(fechanacimientoString.substring(8,10)); //Utilizo el metodo substring, para quedarme solo con el dia y lo convierto a un Int
        edad=Period.between(LocalDate.of(anio,mes,dia),LocalDate.now()).getYears(); // Utilizo la clase Period. y el metodo between, de esta manera le paso la fecha de nacimiento, y lo comparo con el dia actual, esto me va a devolver un objeto de tipo Period con un año, un mes y un día, aprovecho el metodo .getYears para quedarme solo con el año y lo guardo en una variable de dato Int
        return edad;
    }

    private boolean buscarDniRepetido(Clientes cliente){
        clientescopia=clientesRepository.findAll();
        for(int i=0;i<clientescopia.size();i++){
            elementocliente=clientescopia.get(i);
            if(elementocliente.getDni().equals(cliente.getDni())) {
                return true;
            }
        }
        return false;
    }

    private int calcularID(){
        return clientesRepository.findAll().size()+1;
    }

}
