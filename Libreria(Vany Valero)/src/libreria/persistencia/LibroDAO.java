
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Libro;


public class LibroDAO extends DAO<Libro>{
    
    @Override
    public void guardar(Libro libro){
        super.guardar(libro);
    }
    
    public List<Libro> listarTodos() throws Exception{ 
      conectar();
      List<Libro> libros = em.createQuery("SELECT l FROM Libro l").getResultList();
      desconectar();
      
        return libros;
    }
    
    public Libro buscarporISBN(String isbn) throws Exception{
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn LIKE :isbn").setParameter("isbn", isbn).getSingleResult();
        desconectar();
        
        return libro;
    }
    
    public void eliminar(String isbn) throws Exception{ 
        Libro libro = buscarporISBN(isbn);
        super.eliminar(libro);
    }
    
}
