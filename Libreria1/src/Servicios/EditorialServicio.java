
package Servicios;

import java.util.List;
import java.util.Scanner;
import Entidades.Editorial;
import Persistencia.EditorialJpaController;


public class EditorialServicio {
    
    EditorialJpaController ed = new EditorialJpaController();
   
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void menu() {
        int opc;
        boolean running = true;
        try {
            while (running) {
                System.out.println("\nMENU EDITORIAL:");
                System.out.println(""
                        + "1. Crear Editorial\n"
                        + "2. Listar Editoriales\n"
                        + "3. Modificar Editorial\n"
                        + "4. Dar de baja Editorial\n"
                        + "5. Restaurar Editorial\n"
                        + "0. Salir");

                opc = leer.nextInt();

                switch (opc) {
                    case 0:
                        running = false;
                        break;
                    case 1:
                        CrearEditorial();
                        break;
                    case 2:
                        Listar();
                        break;
                    case 3:
                        Listar();
                        System.out.println("\nIngrese el ID de la editorial a modificar:");
                        Editorial e1 = Buscar(leer.nextInt());
                        ModificarEditorial(e1);
                        break;
                    case 4:
                        Listar();
                        System.out.println("\nIngrese el ID de la editorial a dar de baja:");
                        e1 = Buscar(leer.nextInt());
                        AltaBaja(e1);
                        break;
                    case 5:
                        ListarBaja();
                        System.out.println("\nIngrese el ID de la editorial a dar de alta:");
                        e1 = Buscar(leer.nextInt());
                        AltaBaja(e1);
                        break;
                    default:
                        System.out.println("Opcion incorrecta");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void CrearEditorial(){
        System.out.println("Ingrese el nombre de la editorial");
        String nombre = leer.next();
        Boolean alta = true;
        Editorial e = new Editorial(nombre, alta);
        ed.create(e);
    }
    
    public void Listar() {
        try {
            List<Editorial> editoriales = ed.findEditorialEntities();
        for (Editorial a : editoriales) {
            if (a.getAlta()) {
                System.out.println("ID:" + a.getId()+" - NOMBRE: " + a.getNombre());
            }
        }
        } catch (Exception e) {
            System.out.println("No se pudieron listar las editoriales.");
        }
    }
    
    public void ListarBaja() throws Exception{
        List<Editorial> editoriales = ed.findEditorialEntities();
        for (Editorial a : editoriales) {
            if (!a.getAlta()) {
                System.out.println("ID:" + a.getId()+" - NOMBRE: " + a.getNombre());
            }
        }
    }
    
    public Editorial Buscar(int id) {
        Editorial e = null;
        try {
            e = ed.findEditorial(id);
        } catch (Exception ex) {
            System.out.println("No se encontro la editorial.");
        }
        return e;
    }
    
    public void AltaBaja(Editorial e) {
        try {
            if (e.getAlta()) {
                e.setAlta(false);
            } else {
                e.setAlta(true);
            }
            ed.edit(e);
        } catch (Exception ex) {
            System.out.println("No se pudo dar de alta/baja la editorial");
        }
    }
    
    public void ModificarEditorial(Editorial e) throws Exception{
        System.out.println("Ingrese el nuevo nombre");
        String nombre = leer.next();
        e.setNombre(nombre);
        ed.edit(e);
    }

}
