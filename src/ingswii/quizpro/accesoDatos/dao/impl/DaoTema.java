package ingswii.quizpro.accesoDatos.dao.impl;

import ingswii.quizpro.accesoDatos.dao.*;
import ingswii.quizpro.biz.vo.ram.Pregunta;
import ingswii.quizpro.biz.vo.ram.Tema;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
public class DaoTema extends AbstractDao<Tema> implements IDaoTema {

    public List<Tema> buscar(Tema tema) {
        
        String strQuery = "SELECT t FROM " + Tema.class.getSimpleName() + " t WHERE (t.nombre LIKE :nombre AND t.descripcion LIKE :descripcion)";
        Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("nombre", "%" + tema.getNombre() + "%");
        query.setParameter("descripcion", "%" + tema.getDescripcion() + "%");

        List<Tema> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }

    public List<Tema> obtenerPorExamenId(Long examenId) {
        
        String strQuery = "SELECT DISTINCT p.tema FROM " + Pregunta.class.getSimpleName() + " p WHERE (p.examen.id = :examenId)";
        Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("examenId", examenId);

        List<Tema> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }
    

}
