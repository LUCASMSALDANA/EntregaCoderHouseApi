package com.desafioapirest.cliente.repository;

import com.desafioapirest.cliente.model.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante,Integer> {
}
