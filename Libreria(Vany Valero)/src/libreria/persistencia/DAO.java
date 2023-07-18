
package libreria.persistencia;
import javax.persistence.*;

public class DAO<T> {
    
    protected final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("LibreriaPU");
    protected EntityManager em = EMF.createEntityManager();
    
    protected void conectar(){
 //Si se cerro la conexion la volvemos a revivir con este metodo
        if (!em.isOpen()) {
            em = EMF.createEntityManager();
        }
    }
    
    protected void desconectar(){
 //Si la conexion con la base de datos esta abierte con este metodo lo cerramos
        if (em.isOpen()) {
            em.close();
        }
    }
    
    protected void guardar(T objeto){
        conectar();
        //abre la tabla
        em.getTransaction().begin();
        //recibe el objeto
        em.persist(objeto);
        //lo guarda e la BD
        em.getTransaction().commit();
        desconectar();
    }
    
    protected void editar(T objeto){
        conectar();
        //abre la tabla
        em.getTransaction().begin();
        
        em.merge(objeto);
        //lo guarda e la BD
        em.getTransaction().commit();
        desconectar();
    }
    
    protected void eliminar(T objeto){
        conectar();
        //abre la tabla
        em.getTransaction().begin();
        //borra el objeto
        em.remove(objeto);
        //guarda los cambios en la BD
        em.getTransaction().commit();
        desconectar();  
    }
    
    
}
