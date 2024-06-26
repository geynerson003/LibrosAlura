package com.alura.bibliotecAlura1.service;

import com.alura.bibliotecAlura1.model.Autores;
import com.alura.bibliotecAlura1.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepo;

    //Guardar autores
    @Transactional
    public void save(Autores autor){
        autorRepo.save(autor);
    }

    //Buscar autores
    public Optional<Autores> findAutoresByNombre(String nombre){
        return autorRepo.findAutoresByNombre(nombre);
    }

    //Obtener todos los autores
    public List<Autores> obtenerAutores() {
        return autorRepo.findAll();
    }

    //Obtener autores vivos por año
    public List<Autores> obtenerFecha(int year){
        return autorRepo.findAutoresByFecha(year);
    }
}
