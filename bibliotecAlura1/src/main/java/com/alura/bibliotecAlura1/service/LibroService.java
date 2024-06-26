package com.alura.bibliotecAlura1.service;

import com.alura.bibliotecAlura1.model.Lenguajes;
import com.alura.bibliotecAlura1.model.Libro;
import com.alura.bibliotecAlura1.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibrosRepository librosRepository;

    //Guardar los libros
    @Transactional
    public void save(Libro libro){
        librosRepository.save(libro);
    }

    //Buscar por titulo
    public Optional<Libro> BuscarPorTitulo(String title){
        return librosRepository.findLibroByTitulo(title);
    }

    //Obtener todos los libros
    public List<Libro> getAllBooks() {
        return librosRepository.findAll();
    }

    //Obtener libros por lenguaje
    public List<Libro> ListarLenguajes(String lenguajes) {
        try {
            Lenguajes l = Lenguajes.fromString(lenguajes);
            return librosRepository.searchByLenguajes(l);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Idioma no válido.");
            return Collections.emptyList(); // Devuelve una lista vacía en caso de error
        }
    }

}
