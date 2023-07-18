
package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;


public class EditorialServicio {
    
    EditorialDAO ed = new EditorialDAO();
   
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void crearEditorial(){
        
        System.out.println("Ingrese el nombre de la editorial");
        String nombre = leer.next();
        Boolean alta = true;
        Editorial e = new Editorial(nombre, alta);
        ed.guardar(e);
    
    }
    
    public void Listar() throws Exception{
        
        List<Editorial> editoriales = ed.listarTodos();
        for (Editorial aux : editoriales) {
            System.out.println("NOMBRE: " + aux.getNombre());
            System.out.println("ID: " +aux.getId());
        }
    }
    
    public void buscar() throws Exception{
        
        System.out.println("Ingrese el id de la editorial que desee");
        String id = leer.next();
        Editorial e = ed.buscarporId(id);
        
        System.out.println("NOMBRE: "+ e.getNombre());
        System.out.println("ID: "+e.getId());
    }
    
    public void eliminarEditorial() throws Exception{
        
        System.out.println("Ingrese el id de la editorial que desea eliminar");
        String id = leer.next();
        ed.eliminar(id);
        
        System.out.println("La editorial fue eliminada correctamente");
        Listar();
    }
    
    public void modificarEditorial() throws Exception{
        
        Listar();
        System.out.println("Elija el id de la editorial a modificar");
        String id = leer.next();
        
        System.out.println("Ingrese el nuevo nombre");
        String nombre = leer.next();
        
        Editorial e = new Editorial(id, nombre, true);
        ed.guardar(e);
                
    }

}
