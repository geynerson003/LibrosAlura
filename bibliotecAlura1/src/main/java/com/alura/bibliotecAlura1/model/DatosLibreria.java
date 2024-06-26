package com.alura.bibliotecAlura1.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibreria(
        @JsonAlias("results")List<DatosLibros> libros
        ) {
}
