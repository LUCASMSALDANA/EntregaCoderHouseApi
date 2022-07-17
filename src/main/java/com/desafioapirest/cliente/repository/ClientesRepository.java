package com.desafioapirest.cliente.repository;

import com.desafioapirest.cliente.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository // Le indico a SPRINGBOOT, q esta es la interfaz que usaré como Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
}

//Esta interfaz va a permitir conectarnos a la base de datos.
