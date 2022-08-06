package com.desafioapirest.cliente.service.clientes;

import com.desafioapirest.cliente.exception.ApiException;
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
    //*********************************             GET SERVICES                   ***************************************
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
    public ClientesDTO mostrarByDNI(int dni) throws Exception{
        clientescopia=clientesRepository.findAll();
        for(int i =0;i<clientescopia.size();i++){
            if(clientescopia.get(i).getDni().equals(dni)){
                elementocliente=clientescopia.get(i);
                edad = calcularEdad(elementocliente); // LLamo a mi metodo para calcular la edad
                amostrar= new ClientesDTO(elementocliente.getIdcliente(),elementocliente.getDni(), elementocliente.getNombre(), elementocliente.getApellido(), edad); //construyo mi Cliente a mostrar
                return amostrar;
            }
        }
        throw new ApiException("No se encuentra el DNI : "+dni);
    }

    @Override
    public Clientes mostrarOriginalByID(Integer idcliente) throws Exception {
        elementocliente= clientesRepository.findById(idcliente).orElse(null);
        if(elementocliente==null){throw new ApiException("No se encontro ningun cliente con el ID :"+idcliente);}
        return elementocliente;
    }

    @Override
    public ClientesDTO mostrarEdadByID(Integer idcliente) throws Exception {
        elementocliente=clientesRepository.findById(idcliente).orElse(null); //utilizo el repository para buscar por dni
        if(elementocliente==null){
            throw new ApiException("No se encontro ningun cliente con el ID :"+idcliente);
        }
        edad=calcularEdad(elementocliente); // utilizo el metodo para calcular la edad
        amostrar= new ClientesDTO(elementocliente.getIdcliente(), elementocliente.getDni(), elementocliente.getNombre(), elementocliente.getApellido(), edad); // Creo un objeto tipo CLIENTEDTO para mostrar
        return amostrar;
    }

    //*********************************************************************************************************************
    //*********************************             POST SERVICES                   ***************************************
    //*********************************************************************************************************************

    public ClientesDTO nuevoCliente(Clientes cliente) throws Exception {
        boolean dniRepetido = buscarDniRepetido(cliente); //Con este metodo voy a chequear que el cliente a crear no tenga un DNI repetido a alguno ya existente
        if(dniRepetido){
            throw new ApiException("No se puede crear el Cliente, ya que ese DNI se encuentra en nuestra base de datos");
        }else {
            int id = calcularID(); // Calculo el id, no importa el parametro q me pase el usuario, para evitar problemas lo defino desde el programa
            cliente.setIdcliente(id); // Una vez calculado defino ese id  en el cliente.
            clientesRepository.save(cliente); // Grabo el cliente nuevo
            edad = calcularEdad(cliente); // calculo la edad
            amostrar = new ClientesDTO(id, cliente.getDni(), cliente.getNombre(), cliente.getApellido(), edad); //Muestro el cliente recien creado y su edad
            return (amostrar);
        }
    }

    @Override
    public ClientesDTO actualizarCliente(Clientes cliente) throws Exception{
        clientescopia = clientesRepository.findAll();
        finalLista = clientescopia.size();
        if(cliente.getIdcliente()<=finalLista && cliente.getIdcliente()>0){  //Si el IDCliente existe en la base de datos , entonces puedo actualizar;
            if(buscarDniRepetido(cliente)){throw new ApiException("No se puede actualizar, ya que el DNI ingresado pertenece a otro cliente en nuestra base de datos");}
            clientesRepository.save(cliente);
            edad=calcularEdad(cliente);
            amostrar = new ClientesDTO(cliente.getIdcliente(), cliente.getDni(), cliente.getNombre(), cliente.getApellido(), edad);
          return amostrar;
        }
        throw new ApiException("El ID de Cliente no existe");
    }

    //*******************************************************************************************************************
    //*******************************             DELETE SERVICES                   *************************************
    //*******************************************************************************************************************

    @Override
    public String borrarCliente(int id) {
        clientescopia= clientesRepository.findAll();
        String texto = "No se encontró el cliente con el id: "+id+", por lo tanto no se puede eliminar";
        if(id<1){return texto;}
        for(int i=0;i<clientescopia.size();i++){
            if(id==clientescopia.get(i).getIdcliente()) {
                clientesRepository.deleteById(id);
                texto = "El cliente con el id: " + id + " ha sido eliminiado";
                i=clientescopia.size();
            }
        }
        return texto;
    }

    //*********************************************************************************************************************
    //*********************************                  METODOS                    ***************************************
    //*********************************************************************************************************************

    @Override
    public boolean buscarIDCliente(int idcliente) {
        clientescopia=clientesRepository.findAll();
        for(int i=0;i<clientescopia.size();i++){
            if(clientescopia.get(i).getIdcliente()==idcliente) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getNombreApellido(int idcliente) {
        elementocliente = clientesRepository.findById(idcliente).orElse(null);
        return elementocliente.getNombre()+" "+elementocliente.getApellido();
    }


    private int calcularEdad (Clientes cliente){  //Este va a ser el método que devuelva la edad
        fechanacimiento=cliente.getFechanacimiento(); // Guardo la fecha de nacimiento del cliente en una variable tipo Date
        fechanacimientoString=fechanacimiento.toString(); // Esa variable que guarde antes la convierto en String y la guardo en otra variable
        anio=Integer.parseInt(fechanacimientoString.substring(0,4)); // Utilizo el metodo substring, para quedarme solo con el año y lo convierto a un Int
        mes= Integer.parseInt(fechanacimientoString.substring(5,7)); // Utilizo el metodo substring, para quedarme solo con el mes y lo convierto a un Int
        dia= Integer.parseInt(fechanacimientoString.substring(8,10)); //Utilizo el metodo substring, para quedarme solo con el dia y lo convierto a un Int
        edad=Period.between(LocalDate.of(anio,mes,dia),LocalDate.now()).getYears(); // Utilizo la clase Period. y el metodo between, de esta manera le paso la fecha de nacimiento, y lo comparo con el dia actual, esto me va a devolver un objeto de tipo Period con un año, un mes y un día, aprovecho el metodo .getYears para quedarme solo con el año y lo guardo en una variable de dato Int
        return edad;
    }

    private boolean buscarDniRepetido(Clientes cliente){  //Este metodo chequea que el DNI no se encuentre en la base de datos, si lo encuentra devuelve True con ese dni. Si el dni no se repite me devuelve false
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
