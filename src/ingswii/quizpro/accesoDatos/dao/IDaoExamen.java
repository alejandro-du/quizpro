package ingswii.quizpro.accesoDatos.dao;

import ingswii.quizpro.biz.vo.ram.Examen;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public interface IDaoExamen extends IAbstractDao<Examen> {
    
    List<Examen> buscar(Examen examen);

}
