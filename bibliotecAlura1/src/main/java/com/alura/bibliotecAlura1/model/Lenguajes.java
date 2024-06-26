package com.alura.bibliotecAlura1.model;

public enum Lenguajes {
    SPANISH("es"),
    ENGLISH("en"),
    FRENCH("fr"),
    PORTUGUESE("pt"),
    DESCONOCIDO("hu");

    private String lenguajes;

    Lenguajes(String lenguajes){
        this.lenguajes = lenguajes;
    }

    public static Lenguajes fromString(String text) {
        for (Lenguajes lang : Lenguajes.values()) {
            if (lang.lenguajes.equalsIgnoreCase(text)) {
                return lang;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

}
