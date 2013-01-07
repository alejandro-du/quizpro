package ingswii.quizpro.accesoDatos.dao;

import ingswii.quizpro.biz.vo.ram.Pregunta;
import java.util.List;

/**
 * DAO para los VOs de tipo Pregunta.
 * @author Alejandro
 */
public interface IDaoPregunta extends IAbstractDao<Pregunta> {

    List<Pregunta> buscar(Pregunta pregunta);
    
    List<Pregunta> getPreguntasNoContestadas(Long examenId, Long postulanteId);

}
