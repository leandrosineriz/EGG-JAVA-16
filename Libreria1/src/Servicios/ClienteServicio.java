/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Cliente;
import Persistencia.ClienteJpaController;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author itsmi
 */
public class ClienteServicio {
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    ClienteJpaController CJC = new ClienteJpaController();
    
    public void menu() {
        int opc;
        boolean running = true;
        while (running) {
            System.out.println("\nMENU:");
            System.out.println(""
                    + "1. Crear\n"
                    + "2. Modificar\n"
                    + "3. Alta/Baja\n"
                    + "4. Listar\n"
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
                    GuardarCliente(CrearCliente());
                    break;
                case 2:
                    System.out.println("Ingrese el ID del cliente a modificar:");
                    Listar();
                    try {
                        MenuModificar(Buscar(leer.nextInt()));
                    } catch (Exception e) {
                        System.out.println("Error al buscar.");
                    }
                    break;
                case 3:
                    System.out.println("\nIngrese el ID del cliente a dar de baja/alta:");
                    ListarTodos();
                    try {
                        AltaBaja(Buscar(leer.nextInt()));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    Listar();
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        }
    }
    
    public void MenuModificar(Cliente c1) {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        int opc;
        boolean running = true;
        while (running) {
            System.out.println(c1);
            System.out.println("\nMENU:");
            System.out.println(""
                    + "1. Modificar nombre\n"
                    + "2. Modificar apellido\n"
                    + "3. Modificar DNI\n"
                    + "4. Modificar telefono\n"
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
                    ModificarNombre(c1);
                    break;
                case 2:
                    ModificarApellido(c1);
                    break;
                case 3:
                    ModificarDNI(c1);
                    break;
                case 4:
                    ModificarTelefono(c1);
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        }
    }
    
    public void ModificarNombre(Cliente c1) {
        System.out.println("Ingrese el nuevo nombre:");
        String nombre = leer.next();
        c1.setNombre(nombre);
        try {
            CJC.edit(c1);
        } catch (Exception e) {
            System.out.println("No se pudo editar.");
        }
    }
    
    public void ModificarApellido(Cliente c1) {
        System.out.println("Ingrese el nuevo apellido:");
        String apellido = leer.next();
        c1.setApellido(apellido);
        try {
            CJC.edit(c1);
        } catch (Exception e) {
            System.out.println("No se pudo editar.");
        }
    }
    
    public void ModificarDNI(Cliente c1) {
        System.out.println("Ingrese el nuevo DNI:");
        long dni = leer.nextLong();
        c1.setDocumento(dni);
        try {
            CJC.edit(c1);
        } catch (Exception e) {
            System.out.println("No se pudo editar.");
        }
    }
    
    public void ModificarTelefono(Cliente c1) {
        System.out.println("Ingrese el nuevo telefono:");
        String telefono = leer.next();
        c1.setTelefono(telefono);
        try {
            CJC.edit(c1);
        } catch (Exception e) {
            System.out.println("No se pudo editar.");
        }
    }
    
    public Cliente CrearCliente(){
        Cliente c1 = null;
        System.out.println("Ingrese el nombre del cliente:");
        String nombre = leer.next();
        System.out.println("Ingrese el apellido del cliente:");
        String apellido = leer.next();
        System.out.println("Ingrese el dni del cliente");
        Long dni = leer.nextLong();
        System.out.println("Ingrese el telefono del cliente");
        String telefono = leer.next();
        c1 = new Cliente(dni, nombre, apellido, telefono);
        return c1;
    }
    
    public void GuardarCliente(Cliente c1) {
        try {
            if (c1!=null) {
                CJC.create(c1);
            } else {
                throw new Exception("No se pudo guardar el cliente.");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void Listar() {
        List<Cliente> clientes = CJC.findClienteEntities();
        for (Cliente aux : clientes) {
            if (aux.isAlta()) {
                System.out.print("ID: " + aux.getId());
                System.out.print(" - NOMBRE: " + aux.getNombre());
                System.out.print(" - APELLIDO: " + aux.getApellido());
                System.out.print(" - DNI: " + aux.getDocumento());
                System.out.println(" - TELEFONO: " + aux.getTelefono());
            }
        }
    }
    
    public void ListarTodos() {
        List<Cliente> clientes = CJC.findClienteEntities();
        for (Cliente aux : clientes) {
            System.out.print("ID: " + aux.getId());
            System.out.print(" - NOMBRE: " + aux.getNombre());
            System.out.print(" - APELLIDO: " + aux.getApellido());
            System.out.print(" - DNI: " + aux.getDocumento());
            System.out.print(" - TELEFONO: " + aux.getTelefono());
            System.out.println(" - ACTIVO: " + aux.isAlta());
        }
    }

    public Cliente Buscar(int id) throws Exception {
        try {
            Cliente c1 = CJC.findCliente(id);
            if (c1 == null) {
                throw new Exception("No se encontro el libro");
            }
            return c1;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void AltaBaja(Cliente c1) {
        try {
            if (c1.isAlta()) {
                c1.setAlta(false);
            } else {
                c1.setAlta(true);
            }
            CJC.edit(c1);
        } catch (Exception ex) {
            System.out.println("No se pudo dar de alta/baja la editorial");
        }
    }
}
