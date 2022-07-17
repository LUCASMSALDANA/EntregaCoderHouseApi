package com.desafioapirest.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClienteApplication {

	public static void main(String[] args) {

		SpringApplication.run(ClienteApplication.class, args);

		System.out.println("***************************"); //Una simple linea para mostrar que el progrma esta
		System.out.println("**  Aplicacion Levantada **"); // corriendo
		System.out.println("***************************");

	}

}
