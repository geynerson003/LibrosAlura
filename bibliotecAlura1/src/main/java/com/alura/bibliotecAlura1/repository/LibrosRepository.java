package com.alura.bibliotecAlura1.repository;

import com.alura.bibliotecAlura1.model.Lenguajes;
import com.alura.bibliotecAlura1.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibrosRepository extends JpaRepository<Libro, Long> {


    @Query("SELECT b FROM Libro b JOIN b.autores a WHERE b.titulo LIKE %:titulo%")
    Optional<Libro> findLibroByTitulo(@Param("titulo") String titulo);

    @Query("SELECT b FROM Libro b WHERE b.lenguajes = :lenguaje")
    List<Libro> searchByLenguajes(@Param("lenguaje") Lenguajes lenguaje);

}
