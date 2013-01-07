package ingswii.quizpro.accesoDatos.dao.impl;

import ingswii.quizpro.accesoDatos.dao.*;
import ingswii.quizpro.biz.vo.ram.Decision;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
public class DaoDecision extends AbstractDao<Decision> implements IDaoDecision {

    public List<Decision> obtenerPorPostulanteIdYTemaId(Long postulanteId, Long temaId) {
        
        String strQuery = "SELECT d FROM " + Decision.class.getSimpleName() + " d WHERE (d.postulante.id = :postulanteId AND d.pregunta.tema.id = :temaId)";
        Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("postulanteId", postulanteId);
        query.setParameter("temaId", temaId);

        List<Decision> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }

}
