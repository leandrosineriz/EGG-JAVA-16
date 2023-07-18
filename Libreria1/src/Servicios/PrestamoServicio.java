/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Cliente;
import Entidades.Libro;
import Entidades.Prestamo;
import Persistencia.PrestamoJpaController;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author itsmi
 */
public class PrestamoServicio {
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    LibroServicio ls = new LibroServicio();
    ClienteServicio cs = new ClienteServicio();
    PrestamoJpaController pjc = new PrestamoJpaController();
    
    public void menu() {
        int opc;
        boolean running = true;
        while (running) {
            System.out.println("\nMENU:");
            System.out.println(""
                    + "1. Prestar libro\n"
                    + "2. Devolver libro\n"
                    + "3. Listar prestamos\n"
                    + "0. Salir");
            
            try {
                opc = leer.nextInt();
            } catch (Exception e) {
                System.out.println("Ingrese un numero.");
                opc = -1;
            }
            
            switch (opc) {
                case 0:
                    running = false;
                    break;
                case 1:
                    PrestarLibro();
                    break;
                case 2:
                    try {
                        System.out.println("Ingrese el ID del cliente:");
                        cs.Listar();
                        int id = leer.nextInt();
                        Cliente c1 = cs.Buscar(id);
                        DevolverLibro(c1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Ingrese el ID del cliente:");
                        cs.Listar();
                        int id = leer.nextInt();
                        Cliente c1 = cs.Buscar(id);
                        this.ListarPrestamosCliente(c1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        }
    }
    
    public void PrestarLibro() {
        try {
            Prestamo p1 = new Prestamo();
            Libro l1 = ls.Buscar();
            if (l1.getEjemplaresRestantes()!=0) {
                System.out.println("Ingrese el ID del cliente:");
                cs.Listar();
                Cliente c1 = cs.Buscar(leer.nextInt());
                LocalDateTime now = LocalDateTime.now();
                Calendar calendar = new GregorianCalendar(now.getYear(), now.getMonthValue()-1, now.getDayOfMonth());
                p1.setFechaPrestamo(new Date(calendar.getTime().getTime()));
                p1.setCliente(c1);
                p1.setLibro(l1);
                l1.setEjemplaresPrestados(l1.getEjemplaresPrestados()+1);
                l1.setEjemplaresRestantes(l1.getEjemplaresRestantes()-1);
                
                ls.ModificarLibro(l1);
                pjc.create(p1);
            } else {
                System.out.println("No quedan ejemplares restantes.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    public void DevolverLibro(Cliente c1) {
        try {
            System.out.println("Ingrese el ID del prestamo:");
            ListarPrestamosCliente(c1);
            int id= leer.nextInt();
            Prestamo p1 = Buscar(id);
            if (p1 == null) {
                throw new Exception("No se encontro el prestamo.");
            }
            System.out.println("Ingrese el ANIO de devolucion(numero):");
            int anio= leer.nextInt();
            System.out.println("Ingrese el MES de devolucion(numero):");
            int mes= leer.nextInt();
            System.out.println("Ingrese el DIA de devolucion(numero):");
            int dia= leer.nextInt();
            Calendar calendar = new GregorianCalendar(anio, mes-1, dia);
            Date fechaDevolucion = new Date(calendar.getTime().getTime());
            if (p1.getFechaPrestamo().compareTo(fechaDevolucion)>0) {
                throw new Exception("La fecha de devolucion no puede ser anterior a la fecha de prestamo.");
            }
            p1.setFechaDevolucion(fechaDevolucion);
            
            Libro l1 = ls.Buscar(p1.getLibro().getIsbn());
            l1.setEjemplaresPrestados(l1.getEjemplaresPrestados()-1);
            l1.setEjemplaresRestantes(l1.getEjemplaresRestantes()+1);
            ls.ModificarLibro(l1);
            pjc.edit(p1);
            System.out.println("Devolución exitosa.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ListarPrestamosCliente(Cliente c1) {
        for (Prestamo p1 : pjc.findPrestamosDeCliente(c1.getId())) {
            if (p1.getFechaDevolucion()==null) {
                System.out.println(p1);
            }
        }
    }
    
    public Prestamo Buscar(int idPrestamo) {
        return pjc.findPrestamo(idPrestamo);
    }
}