package com.alura.bibliotecAlura1;

import com.alura.bibliotecAlura1.principal.Principal;
import com.alura.bibliotecAlura1.service.ConsumoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecAlura1Application  implements CommandLineRunner {

	@Autowired
	private Principal principal;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecAlura1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		principal.muestraElMenu();
	}
}
