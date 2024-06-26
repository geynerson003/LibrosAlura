package com.alura.bibliotecAlura1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String  titulo;

    @ManyToOne
    @JoinColumn(name = "autores_id")
    private Autores autores;

    @Enumerated(EnumType.STRING)
    private Lenguajes lenguajes;

    public Libro(){}

    public Libro(DatosLibros datosLibros){
        this.titulo = datosLibros.titulo();
        this.lenguajes = Lenguajes.fromString(String.valueOf(datosLibros.lenguajes().get(0)));
        this.autores = new Autores(datosLibros.autores().get(0));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autores getAutores() {
        return autores;
    }

    public void setAutores(Autores autores) {
        this.autores= autores;
    }

    public Lenguajes getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(Lenguajes lenguajes) {
        this.lenguajes = lenguajes;
    }

    @Override
    public String toString() {
        return "Libros " +
                ", titulo " + titulo + '\'' +
                ", autores " + autores.getNombre() +
                ", lenguajes " + lenguajes;
    }
}


