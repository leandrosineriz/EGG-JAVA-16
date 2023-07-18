package libreria.persistencia;

import java.util.List;
import libreria.entidades.Editorial;

public class EditorialDAO extends DAO<Editorial> {

    @Override
    public void guardar(Editorial e) {
        super.guardar(e);
    }

    public List<Editorial> listarTodos() throws Exception {
        conectar();
        List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e").getResultList();
        desconectar();

        return editoriales;
    }

    public Editorial buscarporId(String id) throws Exception {

        conectar();
        Editorial editorial = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.id LIKE :id").setParameter("id", id).getSingleResult();
        desconectar();
        return editorial;
    }

    public void eliminar(String id) throws Exception {

        Editorial editorial = buscarporId(id);
        super.eliminar(editorial);
    }

}
