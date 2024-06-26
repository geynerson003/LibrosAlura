package com.alura.bibliotecAlura1.repository;

import com.alura.bibliotecAlura1.model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autores, Long> {

        Optional<Autores> findAutoresByNombre(String nombre);

        @Query("SELECT a FROM Autores a WHERE a.fechaDeNacimiento <= :year AND a.fechaDeFallecimiento >= :year")
        List<Autores> findAutoresByFecha(int year);
    }


