/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Persona;
import java.util.List;

/**
 *
 * @author itsmi
 */
public class PersonaDAO extends DAO<Persona> {
    @Override
    public void guardar(Persona persona) {
        super.guardar(persona);
    }

    public void eliminar(String id) throws Exception {
        Persona persona = buscarPorDNI(id);
        super.eliminar(persona);
    }

    public List<Persona> listarTodos() throws Exception {
        conectar();
        List<Persona> personas = em.createQuery("SELECT m FROM Persona m ").getResultList();
        desconectar();
        return personas;
    }

    public Persona buscarPorDNI(String id) throws Exception {
        conectar();
        Persona persona = (Persona) em.createQuery("SELECT p FROM Persona p WHERE p.id LIKE :id")
                .setParameter("id", id).getSingleResult();
        desconectar();
        return persona;
    }
}
