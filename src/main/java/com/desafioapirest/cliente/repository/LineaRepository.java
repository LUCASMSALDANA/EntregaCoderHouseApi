package com.desafioapirest.cliente.repository;

import com.desafioapirest.cliente.model.Linea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaRepository extends JpaRepository<Linea,Integer> {
}
