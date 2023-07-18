package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.persistencia.AutorDAO;

public class AutorServicio {

    AutorDAO ad = new AutorDAO();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void crearAutor() {

        System.out.println("Ingrese el nombre del autor");
        String nombre = leer.next();
        Boolean alta = true;
        Autor a = new Autor(nombre, alta);
        ad.guardar(a);

    }

    public void Listar() throws Exception {

        List<Autor> autores = ad.listarTodos();
        for (Autor aux : autores) {
            System.out.println("NOMBRE: " + aux.getNombre());
            System.out.println("ID: " + aux.getId());
        }
    }

    public void buscar() throws Exception {

        System.out.println("Ingrese el id del autor que desee");
        String id = leer.next();
        Autor a = ad.buscarporId(id);

        System.out.println("NOMBRE: " + a.getNombre());
        System.out.println("ID: " + a.getId());

    }

    public void eliminarAutor() throws Exception {

        System.out.println("Ingrese el id del autor que desea eliminar");
        String id = leer.next();
        ad.eliminar(id);
        
        System.out.println("El autor fue eliminado correctamente");
        Listar();
    }
    
    public void modificarAutor() throws Exception{
        
        Listar();
        System.out.println("Elija el id del autor que desea modificar");
        String id = leer.next();
        
        System.out.println("Ingrese el nuevo nombre");
        String nombre = leer.next();
        
        Autor a = new Autor(id, nombre, true);
        ad.editar(a);
    }
}
