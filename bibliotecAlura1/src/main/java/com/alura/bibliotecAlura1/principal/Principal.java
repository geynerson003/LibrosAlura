package com.alura.bibliotecAlura1.principal;

import com.alura.bibliotecAlura1.model.*;
import com.alura.bibliotecAlura1.service.AutorService;
import com.alura.bibliotecAlura1.service.ConsumoAPI;
import com.alura.bibliotecAlura1.service.ConvierteDatos;
import com.alura.bibliotecAlura1.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos convierteDatos = new ConvierteDatos();

   @Autowired
   private LibroService libroService;

   @Autowired
   private AutorService autorService;

   private List<Libro> librosRegistrados;

    public void muestraElMenu(){
        var opcion = -1;

        while (opcion != 0){
            var menu = """
                    Elija la opción a travéz de su número:
                    
                    1- Buscar libros por titulo
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos en un determinado año
                    5- Listar libros por idioma
                    
                    0- Salir
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    buscarPorAnio();
                    break;
                case 5:
                    buscarPorLenguaje();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    // Búsqueda del libro por título en la API
    public void buscarLibroWeb() {
        System.out.print("Ingrese correctamente el titulo a buscar: ");
        var tituloBuscado = teclado.nextLine().toLowerCase();

        // Obtenemos los datos de la API
        String json = consumoAPI.obtenerDatos(URL_BASE + tituloBuscado.replace(" ", "%20"));

        // Convertimos los datos a la clase DatosLibreria
        var datosApi = convierteDatos.obtenerDatos(json, DatosLibreria.class);

        // Filtrar primer libro de la busqueda
        Optional<DatosLibros> libroBuscado = datosApi.libros().stream()
                .filter(d -> d.titulo().toLowerCase().equalsIgnoreCase(tituloBuscado))//Comparar los datos en minusculas
                .findFirst();
        //Comprobar si el libro esta presente, para realizar la conversión
        if (libroBuscado.isPresent()) {
            try {
                // Convertir datos de la API
                List<Libro> libroEncontrado = libroBuscado.stream().map(Libro::new).collect(Collectors.toList());

                Autores autorAPI = libroBuscado.stream()
                        .flatMap(l -> l.autores().stream().map(Autores::new))
                        .findFirst().orElse(null);


                if (autorAPI == null) {
                    System.out.println("No se encontró el autor para el libro.");
                    return;
                }

                Optional<Autores> autorBD = autorService.findAutoresByNombre(
                        libroBuscado.get().autores().stream()
                                .map(DatosAutor::nombre)
                                .collect(Collectors.joining())
                );

                Optional<Libro> libroOptional = libroService.BuscarPorTitulo(tituloBuscado);
                //Comprobar si el libro ya esta guardado en la base de datos
                if (libroOptional.isPresent()) {
                    System.out.println("El libro ya está guardado en la base de datos.");
                } else {
                    Autores autor;
                    //Comprobar si el autor ya esta guardado en la base de datos
                    if (autorBD.isPresent()) {
                        autor = autorBD.get();
                        System.out.println("El autor ya está guardado en la base de datos.");
                    } else {
                        autor = autorAPI;
                        autorService.save(autor);
                    }
                    // Asegurarse de que cada libro tenga el autor asignado y guardar el libro después del autor
                    for (Libro libro : libroEncontrado) {
                        libro.setAutores(autor);
                        libroService.save(libro);
                    }
                }
            } catch (Exception e) {
                System.out.println("Warning! " + e.getMessage());
            }
        } else {
            System.out.println("Libro no encontrado!");
        }

    }
    //Obtener todos los libros de la base de datos
    private void listarLibrosRegistrados() {
        librosRegistrados = libroService.getAllBooks();
        System.out.println("Total de libros: " + librosRegistrados.stream().count());
        librosRegistrados.forEach(System.out::println);
    }

    //Obtener los autores de la base de datos
    private void listarAutoresRegistrados() {
        List<Autores> autoresRegistrados = autorService.obtenerAutores();
        System.out.println("Total de autores: " + autoresRegistrados.stream().count());
        autoresRegistrados.stream().forEach(System.out::println);

    }

    //Realizar la busqueda de autores vivos por año
    private void buscarPorAnio() {
        System.out.print("Ingrese el año para buscar autores vivos: ");
        int year = teclado.nextInt();

        List<Autores> autoresVivos = autorService.obtenerFecha(year);
        System.out.println("Total de autores vivos en " + year + ": " + autoresVivos.stream().count());
        autoresVivos.forEach(System.out::println);
    }

    //Buscar por idioma
    private void buscarPorLenguaje() {
        System.out.print("Español: es" + "\nIngles: en" + "\nFrances: fr" + "\nPortugues: pt\n" + "Ingrese idioma de la forma indicada (es,en,...): ");
        String lang = teclado.nextLine();

        try {
            librosRegistrados = libroService.ListarLenguajes(lang);
            System.out.println("Libros encontrados: " + librosRegistrados.stream().count());
            librosRegistrados.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
