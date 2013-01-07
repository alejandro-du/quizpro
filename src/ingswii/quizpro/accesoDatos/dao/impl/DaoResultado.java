package ingswii.quizpro.accesoDatos.dao.impl;

import ingswii.quizpro.accesoDatos.dao.*;
import ingswii.quizpro.biz.vo.ram.Resultado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
public class DaoResultado extends AbstractDao<Resultado> implements IDaoResultado {

    public boolean eliminarPorExamenId(Long examenId) {
        
        String strQuery = "DELETE FROM " + Resultado.class.getSimpleName() + " r WHERE (r.examen.id = :examenId)";
        EntityManager em = getEntityManager();
        Query query = em.createQuery(strQuery);
        query.setParameter("examenId", examenId);

        return ejecutarUpdate(query, em);
    }

    public List<Resultado> obtenerPrimerosPuntajes(Long examenId, int cantidad) {
        
        String strQuery = "SELECT r FROM " + Resultado.class.getSimpleName() + " r WHERE (r.examen.id = :examenId AND r.tema IS NULL) ORDER BY r.puntaje DESC";
        Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("examenId", examenId);
        query.setFirstResult(0);
        query.setMaxResults(cantidad);

        List<Resultado> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }

    public List<Resultado> obtenerPorPostulanteIdYExamenId(Long postulanteId, Long examenId) {
        
        String strQuery = "SELECT r FROM " + Resultado.class.getSimpleName() + " r WHERE (r.examen.id = :examenId AND r.postulante.id = :postulanteId AND r.tema IS NOT NULL)";
        Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("postulanteId", postulanteId);
        query.setParameter("examenId", examenId);

        List<Resultado> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }

    public Resultado obtenerFinalPorPostulanteIdYExamenId(Long postulanteId, Long examenId) {
        
        String strQuery = "SELECT r FROM " + Resultado.class.getSimpleName() + " r WHERE (r.examen.id = :examenId AND r.postulante.id = :postulanteId AND r.tema IS NULL)";
        Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("postulanteId", postulanteId);
        query.setParameter("examenId", examenId);

        List<Resultado> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista.get(0);
    }

}
