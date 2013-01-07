package ingswii.quizpro.accesoDatos.dao.impl;

import ingswii.quizpro.accesoDatos.dao.IDaoExamen;
import ingswii.quizpro.biz.vo.ram.Examen;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class DaoExamen extends AbstractDao<Examen> implements IDaoExamen {

    public List<Examen> buscar(Examen examen) {
        
        String strQuery = "SELECT e FROM " + Examen.class.getSimpleName() + " e WHERE (e.nombre LIKE :nombre AND e.descripcion LIKE :descripcion)";
        javax.persistence.Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("nombre", "%" + examen.getNombre() + "%");
        query.setParameter("descripcion", "%" + examen.getDescripcion() + "%");
        List<Examen> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }

}
