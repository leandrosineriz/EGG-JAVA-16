
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Autor;



public class AutorDAO extends DAO<Autor> {
    
    @Override
    public void guardar(Autor a){
        super.guardar(a);
    }
    
    public List<Autor> listarTodos() throws Exception{
        
      conectar();
      List<Autor> autores = em.createQuery("SELECT a FROM Libro a").getResultList();
      desconectar();
      
        return autores;
    }
    
    public Autor buscarporId(String id) throws Exception{
        
        conectar();
        Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.id LIKE :id").setParameter("id", id).getSingleResult();
        desconectar();
        return autor;
    }
    
    public void eliminar(String id) throws Exception{
        
        Autor autor = buscarporId(id);
        super.eliminar(autor);
    }
    
    @Override
    public void editar(Autor a){
        super.editar(a);
    }
    
}
