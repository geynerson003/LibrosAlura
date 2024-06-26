package com.alura.bibliotecAlura1.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
