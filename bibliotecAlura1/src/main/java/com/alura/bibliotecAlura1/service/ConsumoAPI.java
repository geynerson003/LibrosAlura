package com.alura.bibliotecAlura1.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String obtenerDatos(String url) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Error al obtener datos. Código de estado: " + response.statusCode());
            }
            String json = response.body();
            return json;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al obtener datos", e);


        }
    }
}
