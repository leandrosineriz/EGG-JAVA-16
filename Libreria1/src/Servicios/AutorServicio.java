package Servicios;

import java.util.List;
import java.util.Scanner;
import Entidades.Autor;
import Persistencia.AutorJpaController;

public class AutorServicio {

    AutorJpaController ad = new AutorJpaController();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    public void menu() {
        int opc;
        boolean running = true;
        try {
            while (running) {
                System.out.println("\nMENU AUTOR:");
                System.out.println(""
                        + "1. Crear autor\n"
                        + "2. Listar autores\n"
                        + "3. Modificar autor\n"
                        + "4. Dar de baja autor\n"
                        + "5. Restaurar autor\n"
                        + "6. Buscar autor por nombre\n"
                        + "7. Buscar por nombre de autor\n"
                        + "0. Salir");

                opc = leer.nextInt();

                switch (opc) {
                    case 0:
                        running = false;
                        break;
                    case 1:
                        CrearAutor();
                        break;
                    case 2:
                        Listar();
                        break;
                    case 3:
                        Listar();
                        System.out.println("\nIngrese el ID del autor a modificar:");
                        Autor a1 = Buscar(leer.nextInt());
                        ModificarAutor(a1);
                        break;
                    case 4:
                        System.out.println("\nIngrese el ID del autor a dar de baja:");
                        a1 = Buscar(leer.nextInt());
                        AltaBaja(a1);
                        break;
                    case 5:
                        ListarBaja();
                        System.out.println("\nIngrese el ID del autor a dar de alta:");
                        a1 = Buscar(leer.nextInt());
                        AltaBaja(a1);
                        break;
                    case 6:
                        System.out.println("\nIngrese el NOMBRE del autor a buscar:");
                        System.out.println(Buscar(leer.next()));
                        break;
                    default:
                        System.out.println("Opcion incorrecta");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void CrearAutor() {
        System.out.println("Ingrese el nombre del autor");
        String nombre = leer.next();
        Boolean alta = true;
        Autor a = new Autor(nombre, alta);
        ad.create(a);
    }

    public void Listar() {
        try {
            List<Autor> autores = ad.findAutorEntities();
            
            for (Autor a : autores) {
            if (a.getAlta()) {
                System.out.println("ID:"+a.getId()+" - NOMBRE: " + a.getNombre());
            }
        }
        } catch (Exception e) {
            System.out.println("No se pudo listar.");
        }
    }
    
    public void ListarBaja() throws Exception {
        List<Autor> autores = ad.findAutorEntities();
        for (Autor a : autores) {
            if (!a.getAlta()) {
                System.out.println("ID:"+a.getId()+" - NOMBRE: " + a.getNombre());
            }
        }
    }

    public Autor Buscar(int id) {
        Autor a = null;
        try {
            a = ad.findAutor(id);
            
        } catch (Exception e) {
            System.out.println("No se encontro el autor.");
        }
        return a;
    }
    
    public List<Autor> Buscar(String nombre) {
        List<Autor> a = null;
        try {
            a = ad.findAutor(nombre);
            
        } catch (Exception e) {
            System.out.println("No se encontro el autor.");
        }
        return a;
    }

    public void AltaBaja(Autor a) throws Exception {
        try {
            if (a.getAlta()) {
                a.setAlta(Boolean.FALSE);
            } else {
                a.setAlta(Boolean.TRUE);
            }
            ad.edit(a);
            System.out.println("El autor fue eliminado correctamente");
        } catch (Exception e) {
            System.out.println("No se pudo eliminar el autor");
            e.printStackTrace();
        }
    }
    
    public void ModificarAutor(Autor a) throws Exception{
        System.out.println("Ingrese el nuevo nombre");
        String nombre = leer.next();
        a.setNombre(nombre);
        ad.edit(a);
    }
}
