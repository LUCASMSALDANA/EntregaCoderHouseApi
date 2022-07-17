package com.desafioapirest.cliente.repository;

import com.desafioapirest.cliente.model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Productos,Integer> {
}
