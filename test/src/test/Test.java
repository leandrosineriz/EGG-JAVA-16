/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entidades.Persona;
import persistencia.PersonaDAO;

/**
 *
 * @author itsmi
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*PersonaDAO pDao = new PersonaDAO();
        Persona p1 = new Persona("Josefafina");
        try {
            for (Persona p : pDao.listarTodos()) {
                System.out.println(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        Persona p1 = new Persona();
        String nombre = null;
        
        try {
            if (nombre == null) {
                p1.setNombre(nombre);
            }
        } catch (Exception e) {
            System.out.println("El nombre es null");
        }
        
    }
    
}
