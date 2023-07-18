package Servicios;

import java.util.List;
import java.util.Scanner;
import Entidades.Autor;
import Entidades.Editorial;
import Entidades.Libro;
import Persistencia.LibroJpaController;

public class LibroServicio {

    LibroJpaController ld = new LibroJpaController();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    EditorialServicio es = new EditorialServicio();
    AutorServicio as = new AutorServicio();

    public void menu() {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        int opc;
        boolean running = true;
        try {
            while (running) {
                System.out.println("\nMENU LIBRO:");
                System.out.println(""
                        + "1. Crear Libro\n"
                        + "2. Listar Libros\n"
                        + "3. Modificar Libro\n"
                        + "4. Eliminar Libro\n"
                        + "5. Buscar libro por ISBN\n"
                        + "6. Buscar libro por titulo\n"
                        + "7. Buscar libro por nombre de autor\n"
                        + "8. Buscar libro por nombre de editorial\n"
                        + "0. Salir");

                opc = leer.nextInt();

                switch (opc) {
                    case 0:
                        running = false;
                    break;
                    case 1:
                        CrearLibro();
                        break;
                    case 2:
                        Listar();
                        break;
                    case 3:
                        System.out.println("Ingrese el ISBN del libro a modificar:");
                        try {
                            MenuModificar(Buscar());
                        } catch (Exception e) {
                            System.out.println("Error al buscar.");
                        }
                        break;
                    case 4:
                        EliminarLibro();
                        break;
                    case 5:
                        System.out.println("Ingrese el ISBN del libro a buscar:");
                        int isbn = leer.nextInt();
                        System.out.println(Buscar(isbn));
                        break;
                    case 6:
                        System.out.println("Ingrese el Titulo del libro a buscar:");
                        String titulo = leer.next();
                        System.out.println(Buscar(titulo));
                        break;
                    case 7:
                        System.out.println("Ingrese el NOMBRE del AUTOR a buscar libros:");
                        String nombre = leer.next();
                        for (Libro li : BuscarPorNombreAutor(nombre)) {
                            System.out.println(li);
                        }
                        break;
                    case 8:
                        System.out.println("Ingrese el NOMBRE de la EDITORIAL a buscar libros:");
                        nombre = leer.next();
                        for (Libro li : BuscarPorNombreEditorial(nombre)) {
                            System.out.println(li);
                        }
                        break;
                    default:
                        System.out.println("Opcion incorrecta");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void MenuModificar(Libro l) {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        int opc;
        boolean running = true;
        while (running) {
            System.out.println(l);
            System.out.println("\nMENU:");
            System.out.println(""
                    + "1. Modificar titulo\n"
                    + "2. Modificar año de publicacion\n"
                    + "3. Modificar cantidad de ejemplares\n"
                    + "4. Modificar Autor\n"
                    + "5. Modificar Editorial\n"
                    + "0. Salir");
            
            try {
                opc = leer.nextInt();
            } catch (Exception e) {
                System.out.println("Ingrese un numero.");
                opc = 0;
            }
            
            switch (opc) {
                case 0:
                    running = false;
                    break;
                case 1:
                    ModificarTitulo(l);
                    break;
                case 2:
                    ModificarAnio(l);
                    break;
                case 3:
                    ModificarNumeroEjemplares(l);
                    break;
                case 4:
                    ModificarAutor(l);
                    break;
                case 5:
                    ModificarEditorial(l);
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        }
    }
    
    public void ModificarTitulo(Libro l) {
        System.out.println("Ingrese el nuevo titulo:");
        String nombre = leer.next();
        l.setTitulo(nombre);
        try {
            ld.edit(l);
        } catch (Exception e) {
            System.out.println("No se pudo editar.");
        }
    }
    
    public void ModificarAnio(Libro l) {
        System.out.println("Ingrese el nuevo año de publicacion");
        Integer anio = leer.nextInt();
        l.setAnio(anio);
        try {
            ld.edit(l);
        } catch (Exception e) {
            System.out.println("No se pudo editar.");
        }
    }
    
    public void ModificarNumeroEjemplares(Libro l) {
        System.out.println("Ingrese el nuevo numero de ejemplares");
        Integer ejemplares = leer.nextInt();
        l.setEjemplares(ejemplares);
        try {
            ld.edit(l);
        } catch (Exception e) {
            System.out.println("No se pudo editar.");
        }
    }
    
    public void ModificarAutor(Libro l) {
        System.out.println("Ingrese el ID del nuevo autor:");
        as.Listar();
        Integer id = leer.nextInt();
        l.setAutor(as.Buscar(id));
        try {
            ld.edit(l);
        } catch (Exception e) {
            System.out.println("No se pudo editar.");
        }
    }
    
    public void ModificarEditorial(Libro l) {
        System.out.println("Ingrese el ID de la nueva editorial:");
        es.Listar();
        Integer id = leer.nextInt();
        l.setEditorial(es.Buscar(id));
        try {
            ld.edit(l);
        } catch (Exception e) {
            System.out.println("No se pudo editar.");
        }
    }
    
    public void CrearLibro() throws Exception {
        System.out.println("Ingrese el ISBN del libro");
        int isbn = leer.nextInt();
        System.out.println("Ingrese el titulo del libro");
        String titulo = leer.next();
        System.out.println("Ingrese el año de publicacion");
        Integer anio = leer.nextInt();
        System.out.println("Ingrese la cantidad de ejemplares");
        Integer ejemplares = leer.nextInt();
        Boolean alta = true;
        
        Autor autor = null;
        try {
            as.Listar();
            System.out.println("\nIngrese el ID del autor:");
            autor = as.Buscar(leer.nextInt());
        } catch (Exception e) {
            System.out.println("Error de autor");
            throw e;
        }
        
        Editorial editorial = null;
        try {
            es.Listar();
            System.out.println("\nIngrese el ID de la editorial:");
            editorial = es.Buscar(leer.nextInt());
        } catch (Exception e) {
            System.out.println("Error de Editorial");
            throw e;
        }

        Libro l = new Libro(isbn, titulo, anio, ejemplares, 0, ejemplares, alta, autor, editorial);
        ld.create(l);
    }
    
    public void ModificarLibro(Libro l) throws Exception {
        try {
            ld.edit(l);
        } catch (Exception e) {
            throw e;
        }
        
    }

    public void Listar() throws Exception {

        List<Libro> libros = ld.findLibroEntities();
        for (Libro aux : libros) {
            System.out.print("ISBN: " + aux.getIsbn());
            System.out.print(" - TITULO: " + aux.getTitulo());
            System.out.print(" - AUTOR: " + aux.getAutor().getNombre());
            System.out.print(" - EDITORIAL: " + aux.getEditorial().getNombre());
            System.out.print(" - AÑO DE PUBLICACION: " + aux.getAnio());
            System.out.println(" - TOTAL DE EJEMPLARES: " + aux.getEjemplares());
        }
    }

    public Libro Buscar() throws Exception {
        try {
            Listar();
            System.out.println("Ingrese el isbn del libro que desee");
            int isbn = leer.nextInt();
            Libro l = ld.findLibro(isbn);
            if (l == null) {
                throw new Exception("No se encontro el libro");
            }
            return l;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    
    
    public Libro Buscar(int isbn) throws Exception {
        try {
            Libro l = ld.findLibro(isbn);
            if (l == null) {
                throw new Exception("No se encontro el libro");
            }
            return l;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public Libro Buscar(String titulo) throws Exception {
        try {
            Libro l = ld.findLibro(titulo);
            if (l == null) {
                throw new Exception("No se encontro el libro");
            }
            return l;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public List<Libro> BuscarPorNombreAutor(String nombreAutor) throws Exception {
        try {
            List<Libro> l = ld.findLibroPorNombreAutor(nombreAutor);
            if (l == null) {
                throw new Exception("No se encontro el libro");
            }
            return l;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public List<Libro> BuscarPorNombreEditorial(String nombreEditorial) throws Exception {
        try {
            List<Libro> l = ld.findLibroPorNombreEditorial(nombreEditorial);
            if (l == null) {
                throw new Exception("No se encontro el libro");
            }
            return l;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public void EliminarLibro() throws Exception {
        Listar();
        System.out.println("Ingrese el isbn del libro que desea eliminar");
        int isbn = leer.nextInt();
        ld.destroy(isbn);

        System.out.println("EL libro fue eliminado correctamente");
        Listar();

    }

    

}
