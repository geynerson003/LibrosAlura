# LibrosAlura
Proyecto biblioteca, Grupo 6 one oracle, alura.

Este proyecto es una aplicación de biblioteca que interactúa con la API Gutendex para buscar libros y los almacena en una base de datos local. Permite buscar libros por título, listar libros y autores registrados, buscar autores vivos en un año específico y buscar libros por idioma.

## Requisitos

Para ejecutar este proyecto, necesitarás tener instalado:

- Java
- Spring Framework
- Maven
- PostgreSQL (para la base de datos local)

## Configuración

1. Clona el repositorio desde GitHub:
   https://github.com/geynerson003/LibrosAlura.git

2. Importa el proyecto en tu IDE preferido como un proyecto Maven.

3. Configura tu base de datos PostgreSQL en `application.properties`.

4. Ejecuta la aplicación.

## Uso

1. Ejecuta la aplicación. Se iniciará un menú interactivo en la consola.

2. Selecciona una opción del menú ingresando el número correspondiente y presionando Enter.

- **Buscar libros por título**: Permite buscar un libro específico por su título en la API Gutendex y guardarlo en la base de datos local si no está registrado.

- **Listar libros registrados**: Muestra todos los libros almacenados en la base de datos local.

- **Listar autores registrados**: Muestra todos los autores almacenados en la base de datos local.

- **Buscar autores vivos en un año**: Busca y muestra autores que estaban vivos en un año específico.

- **Buscar libros por idioma**: Busca y muestra libros por un idioma específico almacenados en la base de datos local.

3. Para salir de la aplicación, selecciona la opción `0`.

## Contribuir

¡Contribuciones son bienvenidas! Si quieres contribuir a este proyecto, sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Haz tus cambios y haz commit (`git commit -am 'Añadir nueva funcionalidad'`).
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un pull request.

## Creador 
GEYNERSON LEAL GUZMAN