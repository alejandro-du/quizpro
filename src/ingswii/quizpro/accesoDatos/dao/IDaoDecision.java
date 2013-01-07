package ingswii.quizpro.accesoDatos.dao;

import ingswii.quizpro.biz.vo.ram.Decision;
import java.util.List;

/**
 * DAO para los VOs de tipo Decision.
 * @author Alejandro
 */
public interface IDaoDecision extends IAbstractDao<Decision> {
    
    public List<Decision> obtenerPorPostulanteIdYTemaId(Long postulanteId, Long temaId);
    
}
