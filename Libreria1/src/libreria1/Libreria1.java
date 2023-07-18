/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria1;

import Servicios.AutorServicio;
import Servicios.ClienteServicio;
import Servicios.EditorialServicio;
import Servicios.LibroServicio;
import Servicios.PrestamoServicio;
import java.util.Scanner;



/**
 *
 * @author itsmi
 */
public class Libreria1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        AutorServicio as = new AutorServicio();
        EditorialServicio es = new EditorialServicio();
        LibroServicio ls = new LibroServicio();
        ClienteServicio cs = new ClienteServicio();
        PrestamoServicio ps = new PrestamoServicio();
        int opc;
        
        while (true) {
            System.out.println("\nMENU:");
            System.out.println(""
                    + "1. Opciones Autor\n"
                    + "2. Opciones Editorial\n"
                    + "3. Opciones Libro\n"
                    + "4. Opciones Cliente\n"
                    + "5. Opciones Prestamo");
            
            opc = leer.nextInt();
            
            switch (opc) {
                case 1:
                    as.menu();
                    break;
                case 2:
                    es.menu();
                    break;
                case 3:
                    ls.menu();
                    break;
                case 4:
                    cs.menu();
                    break;
                case 5:
                    ps.menu();
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        }
    }
    
}
