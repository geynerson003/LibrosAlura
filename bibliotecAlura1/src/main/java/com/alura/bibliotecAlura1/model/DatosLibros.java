package com.alura.bibliotecAlura1.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("languages") List<String> lenguajes

) {

    @Override
    public String toString() {
        return "DatosLibros " +
                "titulo " + titulo + '\'' +
                ", autores " + autores +
                ", lenguajes " + lenguajes;
    }
}
