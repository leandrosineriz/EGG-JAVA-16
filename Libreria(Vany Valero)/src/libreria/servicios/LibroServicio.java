package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDAO;

public class LibroServicio {

    LibroDAO ld = new LibroDAO();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void crearLibro() {

        System.out.println("Ingrese el titulo del libro");
        String titulo = leer.next();
        System.out.println("Ingrese el año de publicacion");
        Integer anio = leer.nextInt();
        System.out.println("Ingrese la cantidad de ejemplares");
        Integer ejemplares = leer.nextInt();
        System.out.println("Ingrese la cantidad de ejemplares que fueron prestados");
        Integer ejemplaresprestados = leer.nextInt();
        System.out.println("Ingrese la cantidad de ejemplares restantes");
        Integer ejemplaresrestantes = leer.nextInt();
        Boolean alta = true;
        Autor autor = new Autor();
        System.out.println("Ingrese el nombre del autor");
        autor.setNombre(leer.next());
        Editorial editorial = new Editorial();
        System.out.println("Ingrese el nombre de la editorial");
        editorial.setNombre(leer.next());

        Libro l = new Libro(titulo, anio, ejemplares, ejemplaresprestados, ejemplaresrestantes, alta, autor, editorial);
        ld.guardar(l);
    }

    public void Listar() throws Exception {

        List<Libro> libros = ld.listarTodos();
        for (Libro aux : libros) {
            System.out.println("TITULO: " + aux.getTitulo());
            System.out.println("ID: " + aux.getIsbn());
            System.out.println("AUTOR: " + aux.getAutor());
            System.out.println("EDITORIAL: " + aux.getEditorial());
            System.out.println("AÑO DE PUBLICACION: " + aux.getAnio());
            System.out.println("TOTAL DE EJEMPLARES: " + aux.getEjemplares());
            System.out.println("EJEMPLARES PRESTADOS" + aux.getEjemplaresPrestados());
            System.out.println("EJEMPLARES DISPONIBLES: " + aux.getEjemplaresRestantes());

        }
    }

    public void buscar() throws Exception {

        System.out.println("Ingrese el isbn del libro que desee");
        String isbn = leer.next();
        Libro l = ld.buscarporISBN(isbn);

        System.out.println("TITULO: " + l.getTitulo());
        System.out.println("AUTOR: " + l.getAutor());
        System.out.println("EDITORIAL: " + l.getEditorial());
        System.out.println("AÑO DE PUBLICACION: " + l.getAnio());
        System.out.println("TOTAL DE EJEMPLARES: " + l.getEjemplares());
        System.out.println("EJEMPLARES PRESTADOS" + l.getEjemplaresPrestados());
        System.out.println("EJEMPLARES DISPONIBLES: " + l.getEjemplaresRestantes());
    }

    public void eliminarLibro() throws Exception {

        System.out.println("Ingrese el isbn del libro que desea eliminar");
        String isbn = leer.next();
        ld.eliminar(isbn);

        System.out.println("EL libro fue eliminado correctamente");
        Listar();

    }

    public void modificarLibro() throws Exception {

        Listar();
        System.out.println("Elija el isbn del libro que desea modificar");
        String isbn = leer.next();
        System.out.println("Ingrese el nuevo Titulo");
        String nombre = leer.next();
        System.out.println("Ingrese el año de publicacion");
        Integer anio = leer.nextInt();
        System.out.println("Ingrese el numero de ejemplares");
        Integer ejemplares = leer.nextInt();
        System.out.println("Ingrese el numero de ejemplares prestados");
        Integer ejemplaresprestados = leer.nextInt();
        System.out.println("Ingrese el numero de ejemplares disponibles");
        Integer ejemplaresrestantes = leer.nextInt();
        Autor autor = new Autor();
        System.out.println("Ingrese el nombre del autor");
        autor.setNombre(leer.next());
        Editorial editorial = new Editorial();
        System.out.println("Ingrese el nombre de la Editorial");
        editorial.setNombre(leer.next());

        Libro l = new Libro(isbn, anio, ejemplares, ejemplaresprestados, ejemplaresrestantes, Boolean.TRUE, autor, editorial);
        ld.guardar(l);

    }

}
