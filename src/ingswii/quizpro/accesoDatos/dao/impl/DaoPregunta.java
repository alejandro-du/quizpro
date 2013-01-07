package ingswii.quizpro.accesoDatos.dao.impl;

import ingswii.quizpro.accesoDatos.dao.*;
import ingswii.quizpro.biz.vo.ram.Decision;
import ingswii.quizpro.biz.vo.ram.Pregunta;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
public class DaoPregunta extends AbstractDao<Pregunta> implements IDaoPregunta {

    public List<Pregunta> buscar(Pregunta pregunta) {
        
        String strQuery = "SELECT p FROM " + Pregunta.class.getSimpleName() + " p WHERE (p.examen.id = :examenId AND p.texto LIKE :texto)";
        Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("examenId", pregunta.getExamen().getId());
        query.setParameter("texto", "%" + pregunta.getTexto() + "%");

        List<Pregunta> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }

    public List<Pregunta> getPreguntasNoContestadas(Long examenId, Long postulanteId) {
        
        String strQuery = "SELECT p FROM " + Pregunta.class.getSimpleName() + " p WHERE (p.examen.id = :examenId) AND p.id NOT IN (SELECT d.pregunta.id FROM " + Decision.class.getSimpleName() + " d WHERE d.postulante.id = :postulanteId)";
        Query query = getEntityManager().createQuery(strQuery);
        query.setParameter("examenId", examenId);
        query.setParameter("postulanteId", postulanteId);

        List<Pregunta> lista = ejecutarSelect(query);
        
        if(lista == null || lista.size() <= 0) {
            return null;
        }
        
        return lista;
    }

}
